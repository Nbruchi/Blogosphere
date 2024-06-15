package com.blogosphere.backend.services;

import com.blogosphere.backend.models.Post;
import com.blogosphere.backend.models.UserInfo;
import com.blogosphere.backend.repositories.PostRepo;
import com.blogosphere.backend.repositories.UserInfoRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {
    private final PostRepo postRepo;
    private final UserInfoRepo userRepo;

    public PostService(PostRepo postRepo, UserInfoRepo userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    public Post createPost(UUID userId, String title, String description, MultipartFile thumbnail) {
        Optional<UserInfo> optionalUser = userRepo.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Invalid user id");
        }

        UserInfo user = optionalUser.get();

        Post post = new Post(title, description);
        post.setUser(user);

        try {
            if (thumbnail != null) {
                post.setThumbnail(thumbnail.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to store thumbnail", e);
        }

        return postRepo.save(post);
    }

    public Post updatePost(UUID postId, String title, String description, MultipartFile thumbnail) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));

        if (title != null) {
            post.setTitle(title);
        }

        if (description != null) {
            post.setDescription(description);
        }

        try {
            if (thumbnail != null) {
                post.setThumbnail(thumbnail.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to store thumbnail", e);
        }

        return postRepo.save(post);
    }

    public void deletePost(UUID postId) {
        if (!postRepo.existsById(postId)) {
            throw new IllegalArgumentException("Post not found");
        }
        postRepo.deleteById(postId);
    }

    public List<Post> getPostsByUserId(UUID userId) {
        UserInfo user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return postRepo.findByUser(user);
    }

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }
}
