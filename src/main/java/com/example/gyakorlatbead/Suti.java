package com.example.gyakorlatbead;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Entity
@Table(name = "suti")
public class Suti {
@Id
private int id;
@Column(name = "nev")
private String nev;
@Column(name = "tipus")
private String tipus;
@Column(name = "dijazott")
private boolean dijazott;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public boolean isDijazott() {
        return dijazott;
    }

    public void setDijazott(boolean dijazott) {
        this.dijazott = dijazott;
    }

    //Restful
    public Suti() {
    }

    public Suti(String nev, String tipus, boolean dijazott) {
        this.nev = nev;
        this.tipus = tipus;
        this.dijazott = dijazott;
    }
}
