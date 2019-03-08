package com.thompson.ppmtool.services;

import com.thompson.ppmtool.domain.Project;
import com.thompson.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
This class creates a service for the project object. It is autowired to the project repository
so that it can perform crud operations. I separate this service class from controller because
I do not want to cram the controller with a lot of logic.
 */

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    public Project saveOrUpdateProject(Project project){

        //Logic here once I start adding users and logs

        return projectRepository.save(project);
    }
}
