package com.Demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Demo.model.File;

public interface FileRepository extends JpaRepository<File,Integer>{

}
