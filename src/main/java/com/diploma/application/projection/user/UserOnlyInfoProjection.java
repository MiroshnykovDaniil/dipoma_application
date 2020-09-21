package com.diploma.application.projection.user;

import com.diploma.application.model.Role;
import com.diploma.application.model.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name = "User without any further info",types = {User.class})
public interface UserOnlyInfoProjection {

    String getName();
    String getEmail();
    Set<Role> getRoles();
    String getId();
    String getImageUrl();
}
