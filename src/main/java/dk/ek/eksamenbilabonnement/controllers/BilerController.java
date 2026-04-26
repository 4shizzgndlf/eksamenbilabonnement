package dk.ek.eksamenbilabonnement.controllers;

import dk.ek.eksamenbilabonnement.services.BilerService;
import dk.ek.eksamenbilabonnement.services.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BilerController {
    private final BilerService bilerService;

    public BilerController(BilerService bilerService) {
        this.bilerService = bilerService;
    }

    @GetMapping("/biler")
    public ModelAndView biler() {
        ModelAndView mav = new ModelAndView("biler");
        return mav;
    }
}
