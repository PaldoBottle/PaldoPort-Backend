package paldo_bottle.backend.DAO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import paldo_bottle.backend.DAO.identifier.AchievePK;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@IdClass(AchievePK.class)
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user_achieve_challenge")
public class Achieve {

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="id")
    private User userId;

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="name")
    private Challenge challengeName;

    @Column
    private LocalDateTime   publish_date;

    @Column
    private Long            public_number;
}
