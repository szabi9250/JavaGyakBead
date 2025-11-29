package com.example.gyakorlatbead;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SutiRepo extends CrudRepository<Suti, Integer>{
    @Query("SELECT i.tipus, COUNT(i) FROM Suti i GROUP BY i.tipus")
    List<Object[]> countByTipus();
    @Query("SELECT s, t, a.ertek, a.egyseg FROM Suti s " +
            "LEFT JOIN Tartalom t ON t.sutiid = s.id " +
            "LEFT JOIN Ar a ON a.sutiid = s.id")
    List<Object[]> fullData();
}
