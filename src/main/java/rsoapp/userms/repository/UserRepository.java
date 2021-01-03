package rsoapp.userms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rsoapp.userms.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByEmail(String username);
}
