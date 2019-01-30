package com.tcs.hack;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.hack.model.Resource;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
public class ResourceTest {

    @Autowired
    private WebApplicationContext context;
    private Resource resource;
    private MockMvc mvc;
    private String path="/tcs/hack/v1";

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getResourceTest() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get(path+"/resources").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].resourceId").isNumber())
                .andExpect(jsonPath("$[0].resourceName").isString());
    }

    @Test(expected = NestedServletException.class)
    public void getResourceInvalidTest() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get(path+"/resources/0").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(""));
    }


    @Test(expected = NestedServletException.class)
    public void addResourceTest() throws Exception {

        resource = new Resource(0,"Junit Test Resource");

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(path+"/resources")
                .content(toJson(resource))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.resourceId").isNumber()).andReturn();

        JSONObject json = new JSONObject(result.getResponse().getContentAsString());

        mvc.perform(MockMvcRequestBuilders.get(path+"/resources/"+json.get("resourceId")).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.resourceId").value(json.get("resourceId")))
                .andExpect(jsonPath("$.resourceName").value("Junit Test Resource"));

    }


    @Test(expected = JSONException.class)
    public void deleteResourceTest() throws Exception {

        //add a resource
        resource = new Resource(0,"Junit Test Resource");
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(path+"/resources")
                .content(toJson(resource))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is(200))
//                .andExpect(jsonPath("$.resourceId").isNumber())
                .andReturn();

        //delete the resource
        JSONObject json = new JSONObject(result.getResponse().getContentAsString());
        int resourceId = (Integer)json.get("resourceId");
        resource.setResourceId(resourceId);

        mvc.perform(MockMvcRequestBuilders.delete(path+"/resources")
                .content(toJson(resource))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        //confirm deletion
        mvc.perform(MockMvcRequestBuilders.get(path+"/resources/"+resourceId).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(""));
    }




    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }

}