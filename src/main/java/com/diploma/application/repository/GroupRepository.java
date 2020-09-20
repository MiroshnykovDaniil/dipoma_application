package com.diploma.application.repository;

import com.diploma.application.model.Group;
import com.diploma.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,String> {

    List<Group> findAllByMembers(User user);
}
