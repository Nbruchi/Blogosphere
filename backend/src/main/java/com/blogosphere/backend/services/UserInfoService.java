package com.blogosphere.backend.services;

import com.blogosphere.backend.models.UserInfo;
import com.blogosphere.backend.repositories.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserInfoService {

    private final ClerkService clerkService;
    private final UserInfoRepo userRepo;

    @Autowired
    public UserInfoService(ClerkService clerkService, UserInfoRepo userRepo) {
        this.clerkService = clerkService;
        this.userRepo = userRepo;
    }

    public UserInfo registerUser(String token, String fullName, String username, String email, String hashedPassword, MultipartFile imageUrl) {
        if (!clerkService.validateToken(token)) {
            throw new IllegalArgumentException("Invalid token");
        }

        byte[] imageBytes = convertMultipartFileToBytes(imageUrl);

        UserInfo user = new UserInfo(fullName, username, email, hashedPassword);
        user.setImageUrl(imageBytes);

        return userRepo.save(user);
    }

    private byte[] convertMultipartFileToBytes(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image", e);
        }
    }
}
