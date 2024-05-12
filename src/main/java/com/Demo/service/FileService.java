package com.Demo.service;

import java.util.List;

import com.Demo.model.File;

public interface FileService {
    File getFile(int Fileid);
    List<File> getAllFiles();
}
