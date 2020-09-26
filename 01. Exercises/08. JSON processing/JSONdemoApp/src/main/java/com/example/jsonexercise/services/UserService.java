package com.example.jsonexercise.services;

import com.example.jsonexercise.models.dtos.UserSeedDto;
import com.example.jsonexercise.models.entities.User;

import java.util.Random;

public interface UserService {
    void seedUsers(UserSeedDto[] userSeedDtos);

    User getRandomUser();

}
