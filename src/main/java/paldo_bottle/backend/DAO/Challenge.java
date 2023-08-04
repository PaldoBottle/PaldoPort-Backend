package paldo_bottle.backend.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Challenge {

    @Id
    private String  name;

    @OneToMany(mappedBy = "challengeName")
    private List<Achieve>   achieves;

    @OneToMany(mappedBy = "challengeName")
    private List<StampChallenge>    stampList;

    @Column
    private String  description;

    @Column
    private Long    point;
}
