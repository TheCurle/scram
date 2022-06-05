package uk.gemwire.scram.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Allows accessing AND modifying users through a JpaRepository.
 */
public interface UserRepository extends JpaRepository<UserData, Long> {
    UserData findByEmail(String email);
    UserData findByName(String username);

    @Override
    void delete(UserData user);
}
