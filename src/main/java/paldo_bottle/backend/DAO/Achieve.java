package paldo_bottle.backend.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import paldo_bottle.backend.DAO.identifier.AchievePK;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@IdClass(AchievePK.class)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_achieve_challenge")
public class Achieve {

    @Id
    @ManyToOne
    @JoinColumn(name="id")
    private User userId;

    @Id
    @ManyToOne
    @JoinColumn(name="name")
    private Challenge challengeName;

    @Column
    private LocalDateTime   publish_date;

    @Column
    private Long            public_number;
}
