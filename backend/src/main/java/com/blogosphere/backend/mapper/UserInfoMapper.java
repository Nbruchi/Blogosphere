package com.blogosphere.backend.mapper;

import com.blogosphere.backend.dto.UserInfoDTO;
import com.blogosphere.backend.models.UserInfo;

public class UserInfoMapper {

    public static UserInfoDTO toDTO(UserInfo user) {
        UserInfoDTO dto = new UserInfoDTO();
        dto.setFullName(user.getFullName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setImageUrl(new String(user.getImageUrl()));
        return dto;
    }

    public static UserInfo toEntity(UserInfoDTO dto) {
        UserInfo user = new UserInfo();
        user.setFullName(dto.getFullName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setImageUrl(dto.getImageUrl().getBytes());
        return user;
    }
}
