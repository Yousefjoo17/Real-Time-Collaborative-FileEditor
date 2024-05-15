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
 @GetMapping("/start-server/port={port}")
 public String runServer( @PathVariable ("port") int port) {
     Thread thread = new Thread(() -> {
         Server server = new Server(port);
         server.startServer();
     });
     thread.start();
     return "Server process initiated successfully";
 }

 @GetMapping("/start-client/port={port}&userID={userID}&added={added}&pos={pos}")
 public String runClient( @PathVariable ("port") int port,@PathVariable ("userID") int userID
 ,@PathVariable ("added") String added,@PathVariable ("pos") int pos) {
     Thread thread = new Thread(() -> {
     String IP = "127.0.0.1"; 
     Client client = new Client(IP, port,userID,added,pos);
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
    
    @PutMapping("/files/id={fileid}&newContent={newcontent}")
    public String updateContent(
    @PathVariable ("fileid") int fileID,@PathVariable ("newcontent") String newcontent)
    {
        fileService.updateFileContent(fileID, newcontent);
        return "You have updated Successfully";
    }
    

    /***************************************UserFilePermission*********************************/
    @GetMapping("UserFilepermissions")
    public List<UserFilePermission> getAllUserFilePermissions(){
        return userFilePermissionService.getAllPermissions();
    }
  
}
