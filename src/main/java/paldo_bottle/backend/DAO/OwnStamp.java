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
public class OwnStamp {
    @Id
    @ManyToOne
    @JoinColumn(name="id")
    private User  userId;

    @Id
    @ManyToOne
    @JoinColumn(name="id")
    private Stamp  stampId;

    @Column
    private LocalDateTime   publish_date;

    @Column
    private Long            publish_number;

}

