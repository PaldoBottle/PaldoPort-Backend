package paldo_bottle.backend.DAO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import paldo_bottle.backend.DAO.embedded.OwnStampID;
import paldo_bottle.backend.DAO.identifier.OwnStampPK;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@IdClass(OwnStampPK.class)
@Getter
@Table(name="user_own_stamp")
public class OwnStamp {
//    @Id
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name="id")
//    private User userId;

//    @Id
//    @Column(name = "supDistrict", length = 200, nullable = false, insertable=false, updatable = false)
//    private String supDistrict;

//    @Id
//    @Column(name = "district", length = 200, nullable = false, insertable=false, updatable = false)
//    private String district;

//    @JoinColumn(name = "region")

//    @Id
//    @ManyToOne(fetch = LAZY)
//    @JoinColumns({
//        @JoinColumn(name = "supDistrict", referencedColumnName = "supDistrict"),
//        @JoinColumn(name = "district", referencedColumnName = "district")
//    })
//    private Stamp stamp;

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

    // == 연관 관계 메서드 == //
    public void setStamp(Stamp stamp) {
        this.stamp = stamp;
        this.publish_number = stamp.getPublished();
//        this.supDistrict = stamp.getRegion().getSupDistrict();
//        this.district = stamp.getRegion().getDistrict();
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

