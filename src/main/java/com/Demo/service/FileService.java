package com.Demo.service;

import java.util.List;

import com.Demo.model.File;

public interface FileService {
    String createFile(File file);
    String deleteFile(int fileID);
    File getFile(int id);
    List<File> getAllFiles();
    String writeToFile(int fileID, String diff,int pos); //0 for addition , 1 for delete
    String deleteFromFile(int fileID, int length, int pos);
}
