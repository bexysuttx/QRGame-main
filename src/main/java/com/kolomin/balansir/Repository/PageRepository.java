package com.kolomin.balansir.Repository;

import com.kolomin.balansir.Entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author macbook on 26.02.2022
 */
public interface PageRepository extends JpaRepository<Page,Long> {

    Page findByPage(String path);

}
