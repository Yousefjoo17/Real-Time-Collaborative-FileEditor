package com.Demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Demo.model.File;
import com.Demo.repository.FileRepository;
import com.Demo.service.FileService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FileServiceImpl implements FileService {
        FileRepository fileRepository;
       
    @Override
    public File getFile(int fileid) {
       return fileRepository.findById(fileid).get(); 
    }

    @Override
    public List<File> getAllFiles() {
      return fileRepository.findAll();
    }

    @Override
    public String createFile(File file) {
     fileRepository.save(file);
     return"Success";
    }

    @Override
    public String deleteFile(int id) {
      fileRepository.deleteById(id);
      return"success";
    }


    @Override
    public String writeToFile(int fileID, String diff, int pos) {
            fileRepository.WriteToFile(fileID, diff, pos);
        return "success";
    }

    @Override
    public String deleteFromFile(int fileID,int length, int pos) {
      fileRepository.deletefromFile(fileID, pos, pos);
      return "success";
    }

    
}
