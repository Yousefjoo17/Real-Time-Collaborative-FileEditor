package com.Demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Demo.model.UserFilePermission;
import com.Demo.repository.UserFilePermissionRepository;
import com.Demo.service.UserFilePermissionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserFilePermissionServiceImpl implements UserFilePermissionService{
        UserFilePermissionRepository userFilePermissionRepository;
    @Override
    public List<UserFilePermission> getAllPermissions() {
      return userFilePermissionRepository.findAll();
    }
    @Override
    public String createPermission(UserFilePermission userFilePermission) {
      userFilePermissionRepository.save(userFilePermission);
      return "Success";
    }

}
