package com.Demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Demo.model.UserFilePermission;

public interface UserFilePermissionRepository extends JpaRepository<UserFilePermission,Integer> {

}
