package com.test.salt.domain;

import com.test.salt.listener.KonsumenEntityListener;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@EntityListeners(KonsumenEntityListener.class)
@Data
public class Konsumen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nama;
    private String alamat;
    private String kota;
    private String provinsi;
    private LocalDateTime tgl_registrasi;
    private String status;
}
