package paldo_bottle.backend.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import paldo_bottle.backend.DAO.identifier.OwnStampPK;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(OwnStampPK.class)
@Getter
@Table(name="user_own_stamp")
public class OwnStamp {
    @Id
    @ManyToOne
    @JoinColumn(name="id")
    private User  userId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "supDistrict", referencedColumnName = "supDistrict"),
            @JoinColumn(name = "district", referencedColumnName = "district")
    })
    private Stamp  stamp;

    @Column
    private LocalDateTime   publish_date;

    @Column
    private Long            publish_number;

}

