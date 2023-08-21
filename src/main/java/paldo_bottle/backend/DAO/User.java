package paldo_bottle.backend.DAO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
//    @GeneratedValue
    private String id;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Achieve> achieves = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OwnStamp> ownStamps = new ArrayList<>();

    @Column
    private Long point;
    @Column
    private String address;

    public User(String id) {
        this.id = id;
        this.point = 0L;
        this.address = "";
    }

    public void addStamps(OwnStamp ownStamp) {
        ownStamps.add(ownStamp);
        this.point += ownStamp.getStamp().getPoint();
    }
};
