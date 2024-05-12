package com.Demo.controller;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Demo.model.User;
import com.Demo.service.CloudVendorService;
import com.Demo.service.FileService;
import com.Demo.service.UserFilePermissionService;
import com.Demo.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("Real-Time-Collaborative-Editing")
public class FileEditingController
{
    CloudVendorService cloudVendorService; 
    UserService userService;
    FileService fileService;
    UserFilePermissionService userFilePermissionService;

    @GetMapping("users/{userID}")
    public User getCloudVendorDetails(@PathVariable("userID") int userID)
    {
       return userService.getUser(userID);
    }

    // Read All Cloud Vendor Details from DB
    @GetMapping("/users")
    public List<User> getAllUSers()
    {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public String createCloudVendorDetails(@RequestBody User user)
    {
        userService.createUser(user);
        return "user Created Successfully";
    }

  /*   @PutMapping("/")
    public String updateCloudVendorDetails(@RequestBody CloudVendor cloudVendor)
    {
        cloudVendorService.updateCloudVendor(cloudVendor);
        return "Cloud Vendor Updated Successfully";
    }
    */

    @DeleteMapping("files/{fileID}")
    public String deleteCloudVendorDetails(@PathVariable("fileID") int fileID)
    {
        if (fileService.getAllFiles().isEmpty())
        return"no files";
        fileService.deleteFile(fileID);
        return "File Deleted Successfully";
    }
}
