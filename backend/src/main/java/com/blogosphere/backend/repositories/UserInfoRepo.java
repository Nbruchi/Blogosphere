package com.blogosphere.backend.repositories;

import com.blogosphere.backend.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, UUID> {
    Optional<UserInfo> findUserInfoByEmail(String email);
}
