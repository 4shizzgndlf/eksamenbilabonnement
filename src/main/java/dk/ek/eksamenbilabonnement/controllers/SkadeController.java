package dk.ek.eksamenbilabonnement.controllers;

import dk.ek.eksamenbilabonnement.services.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SkadeController {
    private final IndexService IndexService;

    public SkadeController(IndexService indexService) {
        IndexService = indexService;
    }

    @GetMapping("/skader")
    public ModelAndView skader() {
        ModelAndView mav = new ModelAndView("skader");
        return mav;
    }
}
