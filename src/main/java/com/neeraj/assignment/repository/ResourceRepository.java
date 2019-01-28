package com.neeraj.assignment.repository;

import com.neeraj.assignment.model.Resource;
import org.springframework.data.repository.CrudRepository;

public interface ResourceRepository extends CrudRepository<Resource, Integer> {
}
