package paldo_bottle.backend.DAO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import paldo_bottle.backend.DAO.identifier.OwnStampPK;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(OwnStampPK.class)
@Getter
@Table(name="user_own_stamp")
public class OwnStamp {
    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="id")
    private User userId;

    @Id
    @Column(name = "supDistrict", length = 200, nullable = false, insertable=false, updatable = false)
    private String supDistrict;

    @Id
    @Column(name = "district", length = 200, nullable = false, insertable=false, updatable = false)
    private String district;

    @ManyToOne(fetch = LAZY)
    @JoinColumns({
            @JoinColumn(name = "supDistrict", referencedColumnName = "supDistrict"),
            @JoinColumn(name = "district", referencedColumnName = "district")
    })
    private Stamp stamp;

    @Column
    private LocalDateTime publish_date;

    @Column
    private Long publish_number;

}

