package com.bruce.server.mapper;

import com.bruce.server.dto.UserInfoDTO;
import com.bruce.server.models.UserInfo;

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
