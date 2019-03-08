package com.thompson.ppmtool.web;


/*
I want this class to act more as a router than a place for logic.
 */

import com.thompson.ppmtool.domain.Project;
import com.thompson.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
@RestController is a specialized version of the controller. It includes the @Controller and @ResponseBody
annotations and as a result, simplifies the controller implementation.
@RequestMapping routes request to the api for the project

 */

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<Project> createNewProject(@RequestBody Project project){
        //wire up and save to database
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }


}
