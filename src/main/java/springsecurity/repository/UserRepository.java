package springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springsecurity.model.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);
}
