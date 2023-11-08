package com.test.salt.listener;

import com.test.salt.domain.Konsumen;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;

public class KonsumenEntityListener {

    @PrePersist
    public void setTglRegistrasi(Konsumen konsumen) {
        konsumen.setTgl_registrasi(LocalDateTime.now());
    }
}


