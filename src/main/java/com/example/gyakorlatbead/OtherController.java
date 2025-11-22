package com.example.gyakorlatbead;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OtherController {

    //Kapcsolat oldal
    @Autowired
    private SutiRepo sutiRepo;

    private final UzenetRepo uzenetRepo;

    public OtherController(UzenetRepo uzenetRepo) {
        this.uzenetRepo = uzenetRepo;
    }

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

    //Üzenetek oldal
    @GetMapping("/uzenetek")
    public String osszesUzenet(Model model) {
        model.addAttribute("uzenetek", uzenetRepo.findAll(Sort.by(Sort.Direction.DESC, "ido")));
        return "uzenetek";
    }

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
}
