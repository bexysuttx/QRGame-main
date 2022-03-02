package com.kolomin.balansir.Repository;

import com.kolomin.balansir.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT * FROM main.users_table WHERE login = ?1", nativeQuery = true)
    User getByLogin(String login);
}
