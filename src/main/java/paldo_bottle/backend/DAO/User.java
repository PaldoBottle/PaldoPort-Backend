package paldo_bottle.backend.DAO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue
    private String id;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Achieve> achieves;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<OwnStamp> ownStamps;

    @Column
    private Long point;
    @Column
    private String address;

    public void addStamps(OwnStamp ownStamp) {
        ownStamps.add(ownStamp);
        ownStamp.setUser(this);
        this.point += ownStamp.getStamp().getPoint();
    }
};
