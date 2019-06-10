package com.ppm.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppm.project.domain.Project;
import com.ppm.project.exception.ProjectIdException;
import com.ppm.project.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository repository;
	
	public Project saveOrUpdateProject(Project project){
		try {
			return repository.save(project);
		}catch(Exception e) {
			throw new ProjectIdException("Project ID "+project.getProjectIdentifier().toUpperCase()+" Already Exists");
		}
    }
	
	public Project findProjectByIdentifier(String identifier) {
		Project project = repository.findByProjectIdentifier(identifier);
		
		if(project == null) {
			throw new ProjectIdException("Project Not created from Identifier id  "+identifier);
		}
		
		return project;
	}
	
	public Iterable<Project> findAllProject(){
		return repository.findAll();
	}
	
	public void deleteProjectByIdentifier(String identifier) {
		Project project = repository.findByProjectIdentifier(identifier);
		
		if(project == null)
			throw new ProjectIdException("Identifier "+identifier+ " Not found for project to delete");
		
		repository.delete(project);
		
		
	}

}
