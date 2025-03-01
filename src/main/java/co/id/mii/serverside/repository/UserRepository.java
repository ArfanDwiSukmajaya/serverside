package co.id.mii.serverside.repository;

import co.id.mii.serverside.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(
        value = "SELECT * FROM users WHERE username = ?1",
        nativeQuery = true)
    Collection<User> findAllByUsername(String username);

}
