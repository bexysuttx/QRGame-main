package com.kolomin.balansir.Service;

import com.kolomin.balansir.Entity.PersonalPassword;
import com.kolomin.balansir.Entity.QR;
import com.kolomin.balansir.Form.LoginForm;
import com.kolomin.balansir.Model.QRPersonalAccessModel;
import com.kolomin.balansir.Repository.PersonalPasswordRepository;
import com.kolomin.balansir.Repository.QRRepository;
import com.kolomin.balansir.Util.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.kolomin.balansir.Service.AdminService.*;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS) ////
public class QRService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QRService.class);
    private QRRepository qrRepository;
    private PersonalPasswordRepository personalPasswordRepository;

    @Autowired
    public QRService(QRRepository qrRepository, PersonalPasswordRepository personalPasswordRepository) {
        this.qrRepository = qrRepository;
        this.personalPasswordRepository = personalPasswordRepository;
    }

    public void saveOrUpdate(QR newQR) {
        qrRepository.save(newQR);
    }

    public Iterable<? extends QR> findAll() {
        return qrRepository.findAll();
    }

    public Boolean existsByQRSuffix(String suffix) {
        if (qrRepository.existsByQRSuffix(suffix) != null){
            return true;
        } else {
            return false;
        }
    }

    public QR getBySuffix(String path) {
        return qrRepository.getBySuffix(path);
    }

    public boolean getTeamByQRSuffix(String path) {
        return qrRepository.getTeamByQRSuffix(path);
    }

    public QR getById(Long id) {
        return qrRepository.getById(id);
    }

    public void delete(QR qr) {
        qrRepository.delete(qr);
    }

    public boolean getSecurity(String qr_suffix) {////
            if (qr_group_access.containsKey(qr_suffix) || qr_personal_access.containsKey(qr_suffix)){
                return true;
        }
            return false;////

    }

    public boolean isAccess(LoginForm form) {
            if (qr_group_access.containsKey(form.getQr()) && qr_password.get(form.getQr()).equals(form.getPassword())) {
                return true;
            } else if (qr_personal_access.containsKey(form.getQr()) && qr_personal_password.get(form.getQr()).containsKey(form.getPassword())){
                    removePersonalPassword(form);
                    return true;
            }
            return false;
    }

    private void removePersonalPassword(LoginForm form) {
       Integer use =  qr_personal_password.get(form.getQr()).get(form.getPassword());
       use--;
       List<PersonalPassword> personalPasswords=personalPasswordRepository.getByPassword(form.getPassword());
       for (PersonalPassword personalPassword : personalPasswords) {
           if (personalPassword.getQr().getQr_suffix().equals(form.getQr())) {
               personalPassword.setQuantity(use);
               personalPasswordRepository.save(personalPassword);
           }
       }
       if (use ==0) {
           qr_personal_password.get(form.getQr()).remove(form.getPassword());
       } else {
           qr_personal_password.get(form.getQr()).put(form.getPassword(), use);
       }
      LOGGER.debug("Password used: {}. Uses left: {}",form.getPassword(),use );

    }
//
    @Transactional
    public void generatePersonalPassword(QRPersonalAccessModel personalAccess, QR qr) {
        List<PersonalPassword> passwords = new ArrayList<>();
        for (int i = 0; i < personalAccess.getCount(); i++) {
            PersonalPassword personalPassword = new PersonalPassword();
            personalPassword.setQr(qr);
            personalPassword.setPassword(PasswordUtils.generatePassword(personalAccess.getTemplate(), personalAccess.getLength()));
            personalPassword.setQuantity(personalAccess.getQuantity());
            passwords.add(personalPassword);
        }
        personalPasswordRepository.saveAll(passwords);
    }
    //
    @Transactional
    public void deletePersonalPassword(QR qr) {
        List<PersonalPassword> passwords=personalPasswordRepository.getByPasswordDelete(qr.getId());
        for (PersonalPassword p: passwords) {
            p.setQr(null);
        }
        if (passwords.size()>0){
            personalPasswordRepository.deleteAll(passwords);
        }
    }

}
