package gamestoreapp.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import gamestoreapp.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findOneByEmail(String email);
}