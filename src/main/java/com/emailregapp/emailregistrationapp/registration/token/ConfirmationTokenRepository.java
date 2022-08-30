package com.emailregapp.emailregistrationapp.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByToken(String token);

    @Query("update confirmation_token as ct set ct.confirmedAt=:x where ct.token=:y")
    @Modifying
    @Transactional
    void updateConfirmedAtByToken(@Param("y") String token,@Param("x") LocalDateTime confirmedAt);
}
