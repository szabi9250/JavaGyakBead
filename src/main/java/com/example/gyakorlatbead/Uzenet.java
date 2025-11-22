package com.example.gyakorlatbead;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "uzenetek")
public class Uzenet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nev;
    private String targy;
    private String uzenet;

    @CreationTimestamp
    @Column(name = "ido", nullable = false, updatable = false)
    private LocalDateTime ido;

    public Uzenet() {
    }

    public Uzenet(String nev, String targy, String uzenet) {
        this.nev = nev;
        this.targy = targy;
        this.uzenet = uzenet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getTargy() {
        return targy;
    }

    public void setTargy(String targy) {
        this.targy = targy;
    }

    public String getUzenet() {
        return uzenet;
    }

    public void setUzenet(String uzenet) {
        this.uzenet = uzenet;
    }

    public LocalDateTime getIdo() {
        return ido;
    }

    public void setIdo(LocalDateTime ido) {
        this.ido = ido;
    }
}
