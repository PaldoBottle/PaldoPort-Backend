package paldo_bottle.backend.stamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import paldo_bottle.backend.DAO.Stamp;
import paldo_bottle.backend.DAO.embedded.RegionID;

import java.util.List;

public interface StampRepository extends JpaRepository<Stamp, RegionID> {
    @Query(value = "select s from Stamp s " +
            "left join OwnStamp os on s.location = os.ownStampID.location " +
            "left join User u on os.ownStampID.userId = u.id " +
            "where u.id = :userId")
    List<Stamp> findAllWithUsers(@Param("userId") String userId);
}