package com.diploma.application.repository;

import com.diploma.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByName(String username);

    User findByEmail(String email);


}
