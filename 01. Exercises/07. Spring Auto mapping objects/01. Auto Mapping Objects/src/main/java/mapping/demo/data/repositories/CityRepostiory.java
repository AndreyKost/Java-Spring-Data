package mapping.demo.data.repositories;

import mapping.demo.data.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepostiory extends JpaRepository<City, Long> {
    City findByName(String name);
}
