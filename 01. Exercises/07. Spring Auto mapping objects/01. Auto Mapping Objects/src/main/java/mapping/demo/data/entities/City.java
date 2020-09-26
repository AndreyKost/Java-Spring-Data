package mapping.demo.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City extends BaseEntity {

    private String name;

}
