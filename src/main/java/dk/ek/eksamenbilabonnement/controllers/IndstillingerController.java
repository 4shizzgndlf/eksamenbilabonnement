package dk.ek.eksamenbilabonnement.controllers;

import dk.ek.eksamenbilabonnement.services.IndexService;
import dk.ek.eksamenbilabonnement.services.IndstillingerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndstillingerController {
    private final IndstillingerService indstillingerService;

    public IndstillingerController(IndstillingerService indstillingerService) {
        this.indstillingerService = indstillingerService;
    }

    @GetMapping("/indstillinger")
    public ModelAndView indstillinger() {
        ModelAndView mav = new ModelAndView("indstillinger");
        return mav;
    }
}
