package com.diploma.application.projection.user;

import com.diploma.application.model.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "User without any further info",types = {User.class})
public interface UserOnlyInfoProjection {

    String getName();
    String getEmail();
    String getRoles();
    String getId();
    String getImageUrl();
}
