package com.thompson.ppmtool.services;

import com.thompson.ppmtool.domain.Project;
import com.thompson.ppmtool.exceptions.ProjectIdException;
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

    //Saves and/or updates project in database
    public Project saveOrUpdateProject(Project project){

        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch(Exception e){
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists.");
        }
    }

    //finds the project by identifier
    public Project findProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project ID '" + projectId.toUpperCase() + "' doest not exist.");
        }
        return project;
    }

    //out of the box returns adjacent object that has all the Jason
    //elements or all the objects within that list that we want to display in this case we want to see a list
    //all our projects so that when we load up the dashboard in our UI we can see all the projects
    //associated with our account.
    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Can not delete project with ID: '"+ projectId.toUpperCase() + "'. Project does not exist");
        }
        projectRepository.delete(project);
    }
}
