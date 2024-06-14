package com.blogosphere.backend.services;

import com.blogosphere.backend.models.UserInfo;
import com.blogosphere.backend.repositories.UserInfoRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserInfoService {
    private final ClerkService clerkService;
    private final UserInfoRepo userRepo;

    public UserInfoService(ClerkService clerkService, UserInfoRepo userRepo) {
        this.clerkService = clerkService;
        this.userRepo = userRepo;
    }

    public UserInfo registerUser(String token, String fullName, String username, String email, MultipartFile imageUrl){
        if(!clerkService.validateToken(token)){
            throw new IllegalArgumentException("Invalid token");
        }

        byte[] imageBytes;
        try{
            imageBytes = imageUrl.getBytes();
        }catch (IOException e){
            throw new RuntimeException("Failed to store image",e);
        }

        UserInfo user = new UserInfo(fullName,username,email,"password");
        user.setImageUrl(imageBytes);

        return userRepo.save(user);
    }
}
