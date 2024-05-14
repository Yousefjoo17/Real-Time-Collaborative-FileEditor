package com.Demo.controller;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Demo.exception.CustomNotFoundException;
import com.Demo.model.File;
import com.Demo.model.User;
import com.Demo.model.UserFilePermission;
import com.Demo.service.FileService;
import com.Demo.service.UserFilePermissionService;
import com.Demo.service.UserService;
import com.Demo.webSockets.Client;
import com.Demo.webSockets.Server;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("Real-Time-Collaborative-Editing")
public class FileEditingController
{
    UserService userService;
    FileService fileService;
    UserFilePermissionService userFilePermissionService;


 /**************************************************Web sockets*****************************************/   
    @PostMapping("/start-server")
    public String startServer() {
        Thread thread = new Thread(() -> {
            Server server = new Server();
            server.startServer();
        });
        thread.start();
        return "Server process initiated successfully";
    }

    @PostMapping("/start-client")
    public String startClient() {
        Thread thread = new Thread(() -> {
            String IP = "127.0.0.1"; // Assuming server is running on localhost
        int port = 8080; // Assuming server is running on port 8080
        Client client = new Client(IP, port);
        client.startClient();
        });
        thread.start();
        return "client process initiated successfully";
    }
/******************************************************users*********************************************/
@GetMapping("users/id={userID}")
public User getCloudVendorDetails(@PathVariable("userID") int userID) {
    User user = userService.getUser(userID);
    
    if (user == null) {
        throw new CustomNotFoundException("user is not found");
    }
    return user;
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


    /**********************************************files************************************************/
    @PostMapping("files")
    public String createFile(@RequestBody File file){
                fileService.createFile(file);
                return"File Added successfully";
    }
    
    @DeleteMapping("files/id={fileID}")
    public String deleteCloudVendorDetails(@PathVariable("fileID") int fileID)
    {
        if (fileService.getAllFiles().isEmpty())
        return"no files";
        fileService.deleteFile(fileID);
        return "File Deleted Successfully";
    }

    @GetMapping("files/id={fileid}")
    public File getFile(@PathVariable ("fileid") int fileID){
        return fileService.getFile(fileID);
    }

    @GetMapping("files")
    public List<File> getAllFiles(){
        return fileService.getAllFiles();
    }

    @PutMapping("/files/id={fileid}&diff={diff}&pos={pos}")
    public String WritetoFile(
    @PathVariable ("fileid") int fileID, @PathVariable ("diff") String diff, @PathVariable ("pos") int pos)
    {
        fileService.writeToFile(fileID, diff, pos);
        return "You have written Successfully";
    }

    @PutMapping("/files/id={fileid}&length={length}&pos={pos}")
    public String DeleteFromFile(
    @PathVariable ("fileid") int fileID,@PathVariable ("length") int length ,@PathVariable ("pos") int pos)
    {
        fileService.deleteFromFile(fileID,length, pos);
        return "You have deleted Successfully";
    }
    

    /***************************************UserFilePermission*********************************/
    @GetMapping("UserFilepermissions")
    public List<UserFilePermission> getAllUserFilePermissions(){
        return userFilePermissionService.getAllPermissions();
    }
  
}
