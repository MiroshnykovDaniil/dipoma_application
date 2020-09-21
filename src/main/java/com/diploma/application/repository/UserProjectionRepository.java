package com.diploma.application.repository;

import com.diploma.application.model.User;
import com.diploma.application.projection.user.UserOnlyInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProjectionRepository extends JpaRepository<User,String> {

    UserOnlyInfoProjection getDistinctTopById(String id);
}
