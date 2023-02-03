package org.sevenorganization.int20h2023ttbe.repository;

import org.sevenorganization.int20h2023ttbe.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
