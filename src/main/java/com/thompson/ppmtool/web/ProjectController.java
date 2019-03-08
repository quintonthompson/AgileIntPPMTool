package com.thompson.ppmtool.web;


/*
I want this class to act more as a router than a place for logic.
 */

import com.thompson.ppmtool.domain.Project;
import com.thompson.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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

    //@Valid gives a clean structure of the invalid error
    //Binding results looks at the object and determines if there are any errors
    //Use generic type "?" instead of "Project" because I can return more than just a project like a String for example
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){


        if(result.hasErrors()) {
            return new ResponseEntity<List<FieldError>>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }

        //wire up and save to database
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }


}
