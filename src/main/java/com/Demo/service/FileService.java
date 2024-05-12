package com.Demo.service;

import java.util.List;

import com.Demo.model.File;

public interface FileService {
    String createFile(File file);
    String deleteFile(int fileID);
    File getFile(int id);
    List<File> getAllFiles();
}
