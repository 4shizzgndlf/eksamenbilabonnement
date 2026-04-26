package dk.ek.eksamenbilabonnement.controllers;

import dk.ek.eksamenbilabonnement.services.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BilerController {
    private final IndexService IndexService;

    public BilerController(IndexService indexService) {
        IndexService = indexService;
    }

    @GetMapping("/biler")
    public ModelAndView biler() {
        ModelAndView mav = new ModelAndView("biler");
        return mav;
    }
}
