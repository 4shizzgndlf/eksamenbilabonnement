package dk.ek.eksamenbilabonnement.controllers;

import dk.ek.eksamenbilabonnement.services.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndstillingerController {
    private final IndexService IndexService;

    public IndstillingerController(IndexService indexService) {
        IndexService = indexService;
    }

    @GetMapping("/indstillinger")
    public ModelAndView indstillinger() {
        ModelAndView mav = new ModelAndView("indstillinger");
        return mav;
    }
}
