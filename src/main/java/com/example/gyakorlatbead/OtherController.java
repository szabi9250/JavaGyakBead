package com.example.gyakorlatbead;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OtherController {

    @Autowired
    private SutiRepo sutiRepo;


    @Autowired
    private TartalomRepo tartalomRepo;

    private final UzenetRepo uzenetRepo;

    public OtherController(UzenetRepo uzenetRepo) {
        this.uzenetRepo = uzenetRepo;
    }

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    //Kapcsolat oldala
    @GetMapping("/kapcsolat")
    public String urlapForm(Model model) {
        model.addAttribute("message", new Message());
        return "kapcsolat";
    }

    @PostMapping("/kapcsolatk")
    public String urlapSubmit(
            @Valid @ModelAttribute Message message, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "kapcsolat";
        }
        Uzenet uzenet = new Uzenet(message.getName(), message.getTopic(), message.getContent());
        uzenetRepo.save(uzenet);

        model.addAttribute("attr2", message);

        return "eredmeny";
    }

    //Üzenetek oldala
    @GetMapping("/uzenetek")
    public String osszesUzenet(Model model) {
        model.addAttribute("uzenetek", uzenetRepo.findAll(Sort.by(Sort.Direction.DESC, "ido")));
        return "uzenetek";
    }

    //Diagram oldala
    @GetMapping("/diagram")
    public String showChart(Model model) {
        List<Object[]> results = sutiRepo.countByTipus();
        List<String> labels = new ArrayList<>();
        List<Long> data = new ArrayList<>();

        for(Object[] row : results) {
            labels.add((String) row[0]);
            data.add((Long) row[1]);
        }

        model.addAttribute("labels", labels);
        model.addAttribute("data", data);

        return "diagram";
    }

    //CRUD oldala
    @GetMapping("/crud")
    public String Crud(Model model) { // Dependency injection
        model.addAttribute("tartalmak", tartalomRepo.findAll());
        return "crud";
    }

    @GetMapping("/uj")
    public String newTartalom(Model model) { // Dependency injection
        model.addAttribute("tartalom", new Tartalom());
        return "ujtartalom";
    }

    @PostMapping(value = "/ment")
    public String saveTartalom(@ModelAttribute Tartalom tartalom) {
        tartalomRepo.save(tartalom);
            return "redirect:/";
        }

    @GetMapping("/modosit/{id}")
    public String editTartalom(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("tartalom", tartalomRepo.findById(id));
        return "modosit";
    }

    @GetMapping("/torles/{id}")
    public String deleteTartalom(@PathVariable(name = "id") int id) {
        tartalomRepo.delete(tartalomRepo.findById(id).get());
        return "redirect:/";
    }

}
