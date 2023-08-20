package paldo_bottle.backend.stamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paldo_bottle.backend.DAO.Stamp;
import paldo_bottle.backend.DAO.embedded.RegionID;

public interface StampRepository extends JpaRepository<Stamp, RegionID> {
//    Optional<Stamp> find(String supDistrict, String district);
}
