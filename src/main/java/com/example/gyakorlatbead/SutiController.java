package com.example.gyakorlatbead;

import org.springframework.web.bind.annotation.*;

@RestController
public class SutiController {
    private final SutiRepo sutirepo1;

    SutiController(SutiRepo sutirepo1) {
        this.sutirepo1 = sutirepo1;
    }

    //Restful oldala
    @GetMapping("/restful")
    Iterable<Suti> readAll() {
        return sutirepo1.findAll();
    }

    @GetMapping("/restful/{id}")
    Suti readOne(@PathVariable int id) {
        return sutirepo1.findById(id)
                .orElseThrow(() -> new SutiNotFoundException(id));
    }

    @PostMapping("/restful")
    Suti sutiLoad(@RequestBody Suti ujSuti) {
        return sutirepo1.save(ujSuti);
    }

    @PutMapping("/restful/{id}")
    Suti sutiEdit(@RequestBody Suti adatSuti, @PathVariable int id) {
        return sutirepo1.findById(id)
                .map(a -> {
                    a.setNev(adatSuti.getNev());
                    a.setTipus(adatSuti.getTipus());
                    a.setDijazott(adatSuti.isDijazott());
                    return sutirepo1.save(a);
                })
                .orElseGet(() -> {
                    adatSuti.setId(id);
                    return sutirepo1.save(adatSuti);
                });
    }

    @DeleteMapping("/restful/{id}")
    void deleteSuti (@PathVariable int id){
        sutirepo1.deleteById(id);
    }
}
