package com.neeraj.assignment.repository;

import com.neeraj.assignment.model.Resource;
import com.neeraj.assignment.model.ResourceInventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceInventoryRepository extends CrudRepository<ResourceInventory,Resource> {
}
