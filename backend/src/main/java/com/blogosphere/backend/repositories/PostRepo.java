package com.blogosphere.backend.repositories;

import com.blogosphere.backend.models.Post;
import com.blogosphere.backend.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepo extends JpaRepository<Post, UUID> {
    List<Post> findByUser(UserInfo user);
}
