package com.bruce.server.repositories;

import com.bruce.server.models.Post;
import com.bruce.server.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepo extends JpaRepository<Post, UUID> {
    List<Post> findByUser(UserInfo user);
}
