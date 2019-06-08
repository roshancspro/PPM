package com.ppm.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppm.project.domain.Project;
import com.ppm.project.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository repository;
	
	public Project saveOrUpdateProject(Project project){
        return repository.save(project);
    }

}
