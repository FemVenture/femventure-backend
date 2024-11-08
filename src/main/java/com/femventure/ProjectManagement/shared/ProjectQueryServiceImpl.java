package com.femventure.ProjectManagement.shared;

import com.femventure.ProjectManagement.domain.entity.Project;
import com.femventure.ProjectManagement.domain.interfaces.repository.IProjectRepository;
import com.femventure.ProjectManagement.service.ProjectServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProjectQueryServiceImpl implements ProjectQueryService {

    private final IProjectRepository projectRepository;
    private final ProjectServiceImpl projectService;

    public ProjectQueryServiceImpl(IProjectRepository projectRepository, ProjectServiceImpl projectService) {
        this.projectRepository = projectRepository;
        this.projectService = projectService;
    }

    @Override
    public Project createProject() {
        return null;
    }
}
