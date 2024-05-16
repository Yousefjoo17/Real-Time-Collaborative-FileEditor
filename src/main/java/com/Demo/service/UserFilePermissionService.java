package com.Demo.service;

import java.util.List;

import com.Demo.model.UserFilePermission;

public interface UserFilePermissionService {
    public List<UserFilePermission> getAllPermissions();
    public String createPermission(UserFilePermission userFilePermission);


} 
