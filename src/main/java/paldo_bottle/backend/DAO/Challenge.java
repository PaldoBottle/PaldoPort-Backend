package paldo_bottle.backend.DAO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge {

    @Id
    private String  name;

    @OneToMany(mappedBy = "challengeName")
    private List<Achieve>   achieves;

    @OneToMany(mappedBy = "challengeName", cascade = CascadeType.ALL)
    private List<StampChallenge>    stampList;

    @Column
    private String  description;

    @Column
    private Long    point;
}
