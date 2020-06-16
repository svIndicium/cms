package hu.indicium.cms.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findRefreshTokenByToken(String refreshToken);
}
