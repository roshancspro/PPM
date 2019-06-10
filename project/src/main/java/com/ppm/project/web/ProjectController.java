package com.ppm.project.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ppm.project.domain.Project;
import com.ppm.project.service.MapValidationErrorService;
import com.ppm.project.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	@Autowired
	ProjectService service;
	
	@Autowired
	MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project,
			BindingResult result){
		ResponseEntity<?> validationErrorResponse = mapValidationErrorService.getValidationErrorMap(result);
		if(validationErrorResponse != null) return validationErrorResponse;
		
		Project project1 = service.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectByIdentifier(@PathVariable String projectId){
		
		Project project = service.findProjectByIdentifier(projectId);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Project> findAllProject(){
		return service.findAllProject();
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId){
		service.deleteProjectByIdentifier(projectId);
		
		return new ResponseEntity<String>("Project with Id "+projectId+ " Deleted successfully",HttpStatus.OK);
	}
	

}
