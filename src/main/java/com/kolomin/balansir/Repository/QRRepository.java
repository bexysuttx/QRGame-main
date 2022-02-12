package com.kolomin.balansir.Repository;

import com.kolomin.balansir.Entity.QR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QRRepository extends JpaRepository<QR, Long> {

    @Query(value = "SELECT id FROM main.qr_table WHERE qr_suffix = ?1", nativeQuery = true)
    Long existsByQRSuffix(String qr_suffix);

    @Query(value = "SELECT * FROM main.qr_table WHERE qr_suffix = ?1", nativeQuery = true)
    QR getBySuffix(String path);

    @Query(value = "SELECT team FROM main.qr_table WHERE qr_suffix = ?1", nativeQuery = true)
    boolean getTeamByQRSuffix(String path);
}