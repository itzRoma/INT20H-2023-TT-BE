package org.sevenorganization.int20h2023ttbe.repository;

import org.sevenorganization.int20h2023ttbe.domain.entity.RefreshToken;
import org.sevenorganization.int20h2023ttbe.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);

    void deleteAllByUser(User user);
}
