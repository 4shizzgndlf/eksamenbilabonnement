package dk.ek.eksamenbilabonnement.controllers;

import dk.ek.eksamenbilabonnement.services.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfilController {
    private final IndexService IndexService;

    public ProfilController(IndexService indexService) {
        IndexService = indexService;
    }

    @GetMapping("/profil")
    public ModelAndView profil() {
        ModelAndView mav = new ModelAndView("profil");
        return mav;
    }
}
