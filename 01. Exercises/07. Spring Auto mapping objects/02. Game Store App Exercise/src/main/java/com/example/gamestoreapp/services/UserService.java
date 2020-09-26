package com.example.gamestoreapp.services;

import com.example.gamestoreapp.domain.dtos.UserLoginDto;
import com.example.gamestoreapp.domain.dtos.UserRegisterDto;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();

    boolean isLoggeduserAdmin();

}
