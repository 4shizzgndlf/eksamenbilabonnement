package dk.ek.eksamenbilabonnement.controllers;

import dk.ek.eksamenbilabonnement.services.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {
    private final IndexService IndexService;

    public DashboardController(IndexService indexService) {
        IndexService = indexService;
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView mav = new ModelAndView("dashboard");
        mav.addObject("users", IndexService.getAllUsers());
        return mav;
    }
}
