package com.example.gyakorlatbead;
import jakarta.persistence.*;

@Entity
@Table(name = "tartalom")
public class Tartalom {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
@Column(name = "sutiid")
private int sutiid;
@Column(name = "mentes")
private String mentes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSutiid() {
        return sutiid;
    }

    public void setSutiid(int sutiid) {
        this.sutiid = sutiid;
    }

    public String getMentes() {
        return mentes;
    }

    public void setMentes(String mentes) {
        this.mentes = mentes;
    }
}
