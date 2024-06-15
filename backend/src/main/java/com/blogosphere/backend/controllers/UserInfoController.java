package com.blogosphere.backend.controllers;

import com.blogosphere.backend.models.Post;
import com.blogosphere.backend.models.UserInfo;
import com.blogosphere.backend.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserInfo> registerUser(
            @RequestParam String token,
            @RequestParam String fullName,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String hashedPassword,
            @RequestParam MultipartFile imageUrl
    ) {
        UserInfo user = userInfoService.registerUser(token, fullName, username, email, hashedPassword, imageUrl);
        return ResponseEntity.ok(user);
    }
}