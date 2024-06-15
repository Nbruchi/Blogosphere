package com.blogosphere.backend.controllers;

import com.blogosphere.backend.models.Post;
import com.blogosphere.backend.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(
            @RequestParam UUID userId,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam(required = false) MultipartFile thumbnail
    ) {
        Post post = postService.createPost(userId, title, description, thumbnail);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<Post> updatePost(
            @PathVariable UUID postId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) MultipartFile thumbnail
    ) {
        Post updatedPost = postService.updatePost(postId, title, description, thumbnail);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable UUID userId) {
        List<Post> posts = postService.getPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
}
