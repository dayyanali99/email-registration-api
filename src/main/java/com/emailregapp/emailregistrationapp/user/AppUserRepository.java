package com.emailregapp.emailregistrationapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

    @Query("update user as u set u.isEnabled=:x where u.email=:y")
    @Modifying
    @Transactional
    void updateEnabledByEmail(@Param("y") String email,@Param("x") Boolean enable);
}
