package com.example.gamestoreapp.services.impls;

import com.example.gamestoreapp.domain.dtos.GameAddDto;
import com.example.gamestoreapp.domain.entities.Game;
import com.example.gamestoreapp.repositories.GameRepository;
import com.example.gamestoreapp.services.GameService;
import com.example.gamestoreapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, UserService userService) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {

        if(!this.userService.isLoggeduserAdmin()){
            System.out.println("Logged user is not admin");
            return;
        }
        Game game=this.modelMapper.map(gameAddDto,Game.class);

        this.gameRepository.saveAndFlush(game);
    }
}
