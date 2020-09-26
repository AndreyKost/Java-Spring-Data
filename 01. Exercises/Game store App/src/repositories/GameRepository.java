package gamestoreapp.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import gamestoreapp.model.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {


}