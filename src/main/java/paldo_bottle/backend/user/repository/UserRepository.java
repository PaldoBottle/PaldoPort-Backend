package paldo_bottle.backend.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paldo_bottle.backend.DAO.User;

public interface UserRepository extends JpaRepository<User, String> {
}
