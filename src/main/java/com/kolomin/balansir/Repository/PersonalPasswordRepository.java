package com.kolomin.balansir.Repository;

import com.kolomin.balansir.Entity.PersonalPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalPasswordRepository extends JpaRepository<PersonalPassword, Long> {

    @Query(value = "SELECT * FROM main.personal_password_table WHERE qr_id = ?1", nativeQuery = true)
    PersonalPassword getByQrId(Long id);

    @Query(value = "Select * from main.personal_password_table where qr_id=?1",nativeQuery = true)
    List<PersonalPassword> getByPasswordDelete(Long id);

    @Query(value = "Select * from main.personal_password_table where password=?1",nativeQuery = true)
    List<PersonalPassword> getByPassword(String password);

}
