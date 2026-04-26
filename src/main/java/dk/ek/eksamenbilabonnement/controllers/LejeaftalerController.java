package dk.ek.eksamenbilabonnement.controllers;

import dk.ek.eksamenbilabonnement.services.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LejeaftalerController {
    private final IndexService IndexService;

    public LejeaftalerController(IndexService indexService) {
        IndexService = indexService;
    }

    @GetMapping("/lejeaftaler")
    public ModelAndView lejeaftaler() {
        ModelAndView mav = new ModelAndView("lejeaftaler");
        return mav;
    }
}
