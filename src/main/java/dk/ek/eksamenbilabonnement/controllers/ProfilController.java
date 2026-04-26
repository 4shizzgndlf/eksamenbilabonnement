package dk.ek.eksamenbilabonnement.controllers;

import dk.ek.eksamenbilabonnement.services.IndexService;
import dk.ek.eksamenbilabonnement.services.ProfilService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfilController {
    private final ProfilService profilService;

    public ProfilController(ProfilService profilService) {
        this.profilService = profilService;
    }

    @GetMapping("/profil")
    public ModelAndView profil() {
        ModelAndView mav = new ModelAndView("profil");
        return mav;
    }
}
