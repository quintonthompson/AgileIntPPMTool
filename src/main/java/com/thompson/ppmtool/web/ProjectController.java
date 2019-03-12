package com.thompson.ppmtool.web;


/*
I want this class to act more as a router than a place for logic.
 */

import com.thompson.ppmtool.domain.Project;
import com.thompson.ppmtool.services.MapValidationErrorService;
import com.thompson.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
@RestController is a specialized version of the controller. It includes the @Controller and @ResponseBody
annotations and as a result, simplifies the controller implementation.
@RequestMapping routes request to the api for the project

 */

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    //@Valid gives a clean structure of the invalid error
    //Binding results looks at the object and determines if there are any errors
    //Use generic type "?" instead of "Project" because I can return more than just a project like a String for example
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){

        //Return list of errors
       ResponseEntity<?> errorMap =  mapValidationErrorService.MapValidationErrorService(result);

       if(errorMap != null) return errorMap;

        //wire up and save to database
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }


    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){
        Project project = projectService.findProjectByIdentifier(projectId);

        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId);

        return new ResponseEntity<String>("Project with ID: " + projectId.toUpperCase() + "' was deleted.",HttpStatus.OK);
    }

}
