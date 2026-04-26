package dk.ek.eksamenbilabonnement.controllers;

import dk.ek.eksamenbilabonnement.services.IndexService;
import dk.ek.eksamenbilabonnement.services.SkadeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SkadeController {
    private final SkadeService skadeService;

    public SkadeController(SkadeService skadeService) {
        this.skadeService = skadeService;
    }

    @GetMapping("/skader")
    public ModelAndView skader() {
        ModelAndView mav = new ModelAndView("skader");
        return mav;
    }
}
