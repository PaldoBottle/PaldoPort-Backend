package paldo_bottle.backend.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paldo_bottle.backend.DAO.User;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<User, String> {

}
