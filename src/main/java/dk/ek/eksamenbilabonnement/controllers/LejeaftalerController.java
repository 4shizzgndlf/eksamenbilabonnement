package dk.ek.eksamenbilabonnement.controllers;

import dk.ek.eksamenbilabonnement.services.IndexService;
import dk.ek.eksamenbilabonnement.services.LejeaftaleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LejeaftalerController {
    private final LejeaftaleService lejeaftaleService;

    public LejeaftalerController(LejeaftaleService lejeaftaleService) {
        this.lejeaftaleService = lejeaftaleService;
    }

    @GetMapping("/lejeaftaler")
    public ModelAndView lejeaftaler() {
        ModelAndView mav = new ModelAndView("lejeaftaler");
        return mav;
    }
}
