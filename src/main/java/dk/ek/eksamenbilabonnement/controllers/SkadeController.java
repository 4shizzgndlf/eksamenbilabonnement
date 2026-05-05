package dk.ek.eksamenbilabonnement.controllers;

import dk.ek.eksamenbilabonnement.services.IndexService;
import dk.ek.eksamenbilabonnement.services.SkadeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SkadeController {

    private final SkadeService skadeService;

    public SkadeController(SkadeService skadeService) {
        this.skadeService = skadeService;
    }

    @GetMapping("/skader")
    public ModelAndView skader(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView mav = new ModelAndView("skader");

        mav.addObject("damagedCars", skadeService.getDamagedCars());
        mav.addObject("damages", skadeService.getAllDamages());

        return mav;
    }

    @PostMapping("/skader/opret")
    public String createDamage(
            @RequestParam int bookingId,
            @RequestParam String description,
            @RequestParam double price
    ) {
        skadeService.createDamageAndMarkCarAsDamaged(bookingId, description, price);

        return "redirect:/skader";
    }

    @PostMapping("/skader/slet")
    public String deleteDamage(
            @RequestParam int damageId,
            @RequestParam int bookingId
    ) {
        skadeService.deleteDamageAndMarkCarAvailable(damageId, bookingId);
        return "redirect:/skader";
    }
}
