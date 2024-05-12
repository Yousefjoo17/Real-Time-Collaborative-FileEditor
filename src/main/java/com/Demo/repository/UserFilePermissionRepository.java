package com.Demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Demo.model.UserFilePermission;

public interface UserFilePermissionRepository extends JpaRepository<UserFilePermission,Integer> {
     @Query("SELECT ufp.permission FROM UserFilePermission ufp WHERE ufp.user.id = :userId AND ufp.file.id = :fileId")
    int findPermissionByUserIdAndFileId(@Param("userId") int userId, @Param("fileId") int fileId);
}
