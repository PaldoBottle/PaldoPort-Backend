package paldo_bottle.backend.DAO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge {

    @Id
    private String name;

    @OneToMany(mappedBy = "challengeName", cascade = CascadeType.ALL)
    private List<Achieve> achieves;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    private List<StampChallenge> stampList = new ArrayList<>();

    @Column
    private String description;

    @Column
    private Long point;

    public Challenge(String name, String description, Long point){
        this.name = name;
        this.description = description;
        this.point = point;
    }

    public void add(StampChallenge stampChallenge) {
        stampList.add(stampChallenge);
    }
}
