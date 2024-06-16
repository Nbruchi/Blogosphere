package com.bruce.server.controllers;

import com.bruce.server.models.UserInfo;
import com.bruce.server.services.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class UserInfoController {

    private final UserInfoService userInfoService;

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