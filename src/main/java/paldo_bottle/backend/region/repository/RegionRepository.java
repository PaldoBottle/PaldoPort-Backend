package paldo_bottle.backend.region.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paldo_bottle.backend.DAO.Region;
import paldo_bottle.backend.DAO.identifier.RegionPK;

@Repository
public interface RegionRepository extends JpaRepository<Region, RegionPK> {
}
