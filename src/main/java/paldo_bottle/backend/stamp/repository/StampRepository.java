package paldo_bottle.backend.stamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paldo_bottle.backend.DAO.Stamp;
import paldo_bottle.backend.DAO.identifier.StampPK;

public interface StampRepository extends JpaRepository<Stamp, StampPK> {
}
