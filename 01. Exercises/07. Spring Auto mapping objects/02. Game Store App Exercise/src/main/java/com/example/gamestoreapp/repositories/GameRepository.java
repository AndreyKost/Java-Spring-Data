package com.example.gamestoreapp.repositories;

import com.example.gamestoreapp.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {



}
