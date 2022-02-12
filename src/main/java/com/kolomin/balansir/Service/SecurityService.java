package com.kolomin.balansir.Service;


import com.kolomin.balansir.Entity.QR;
import com.kolomin.balansir.Repository.QRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private QRRepository qrRepository;

    public QR getPasswords(Long id) {
       return qrRepository.getById(id);
    }


}
