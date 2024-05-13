package com.Demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Demo.model.File;

import jakarta.transaction.Transactional;

public interface FileRepository extends JpaRepository<File,Integer>{
    @Modifying
    @Transactional
    @Query(value = "UPDATE File SET content = CONCAT(SUBSTRING(content, 1, :pos), :diff, SUBSTRING(content, :pos + 1)) WHERE FileID = :fileID", nativeQuery = true)
    void WriteToFile(@Param("fileID") int fileID, @Param("diff") String diff, @Param("pos") int pos);

    @Modifying
    @Transactional
    @Query(value = "UPDATE File SET content = CONCAT(SUBSTRING(content, 1, :pos), SUBSTRING(content, :pos + :length + 1)) WHERE FileID = :fileID", nativeQuery = true)
    void deletefromFile(int fileID, int length, int pos);
}
