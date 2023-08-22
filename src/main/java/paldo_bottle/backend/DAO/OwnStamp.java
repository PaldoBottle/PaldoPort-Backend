package paldo_bottle.backend.DAO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import paldo_bottle.backend.DAO.embedded.OwnStampID;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="user_own_stamp")
public class OwnStamp {
    @EmbeddedId
    private OwnStampID ownStampID;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "supDistrict"),
            @JoinColumn(name = "district")
    })
    @MapsId("location")
    private Stamp stamp;

    @Column
    @CreatedDate
    private LocalDateTime publish_date;

    @Column
    private Long publish_number;

    public void setStamp(Stamp stamp) {
        this.stamp = stamp;
        this.publish_number = stamp.getPublished();
    }

    public void setUser(User user) {
        this.user = user;
    }

    static public OwnStamp createOwnStamp(User user, Stamp stamp) {
        OwnStamp ownStamp = new OwnStamp();
        ownStamp.ownStampID = new OwnStampID(
                stamp.getLocation(),
                user.getId()
        );
        ownStamp.setStamp(stamp);
        ownStamp.setUser(user);
        return ownStamp;
    }
}

