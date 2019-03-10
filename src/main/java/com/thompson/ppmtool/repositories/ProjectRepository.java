package com.thompson.ppmtool.repositories;

import com.thompson.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
@Repository annotation is a marker for any class that fulfills the role or stereotype (also known as
Data Access Object or DAO) of a repository. Among the uses of this marker is the automatic translation of exceptions.

CrudRepository mainly provides CRUD functions. CrudRepository<type,ID>

 */

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    //CrudRepository comes with multiple functions like this one.
Project findByProjectIdentifier(String projectId);
}
