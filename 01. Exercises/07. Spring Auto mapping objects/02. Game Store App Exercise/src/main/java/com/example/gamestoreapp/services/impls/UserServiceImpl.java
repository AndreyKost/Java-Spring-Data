package com.example.gamestoreapp.services.impls;

import com.example.gamestoreapp.domain.dtos.UserDto;
import com.example.gamestoreapp.domain.dtos.UserLoginDto;
import com.example.gamestoreapp.domain.dtos.UserRegisterDto;
import com.example.gamestoreapp.domain.entities.Role;
import com.example.gamestoreapp.domain.entities.User;
import com.example.gamestoreapp.repositories.UserRepository;
import com.example.gamestoreapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final ModelMapper modelMapper;
    private UserDto userDto;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        User user=this.modelMapper.map(userRegisterDto,User.class);

        user.setRole(this.userRepository.count()==0 ? Role.ADMIN : Role.USER);

        this.userRepository.saveAndFlush(user);

    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        User user=this.userRepository
                .findByEmail(userLoginDto.getEmail());

        if(user==null){
            System.out.println("Incorrect username / password");
        } else {
            this.userDto=this.modelMapper.map(user,UserDto.class);
            System.out.printf("Successfully logged in %s%n",userDto.getFullName());
        }
    }

    @Override
    public void logout() {
        if(this.userDto==null){
            System.out.println("Cannot log out. No user was logged in.");
        } else {
            System.out.printf("User %s successfully logged out%n",userDto.getFullName());
            this.userDto=null;
        }
    }

    @Override
    public boolean isLoggeduserAdmin() {
        return this.userDto.getRole().equals(Role.ADMIN);
    }
}
