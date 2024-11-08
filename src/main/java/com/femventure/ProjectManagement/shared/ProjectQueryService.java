package com.femventure.ProjectManagement.shared;

import com.femventure.ProjectManagement.domain.entity.Project;
import jakarta.servlet.http.PushBuilder;

public interface ProjectQueryService {

    public abstract Project createProject();
}
