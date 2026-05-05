package dk.ek.eksamenbilabonnement.controllers;

import dk.ek.eksamenbilabonnement.models.Car;
import dk.ek.eksamenbilabonnement.services.BilerService;
import dk.ek.eksamenbilabonnement.services.IndexService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BilerController {
    private final BilerService bilerService;

    public BilerController(BilerService bilerService) {
        this.bilerService = bilerService;
    }

    @GetMapping("/biler")
    public ModelAndView biler(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView mav = new ModelAndView("biler");

        mav.addObject("availableCars", bilerService.getCarsByStatus("TILGÆNGELIG"));
        mav.addObject("rentedCars", bilerService.getCarsByStatus("UDLEJET"));
        mav.addObject("carsForSale", bilerService.getCarsByStatus("KLAR_TIL_SALG"));
        mav.addObject("soldCars", bilerService.getCarsByStatus("SOLGT"));
        mav.addObject("returnedCars", bilerService.getCarsByStatus("AFLEVERET"));

        return mav;
    }

    @PostMapping("/biler/opret")
    public String opretBil(Car car) {
        bilerService.createCar(car);
        return "redirect:/biler";
    }

    @PostMapping("/biler/rediger")
    public String updateCar(Car car) {
        bilerService.updateCar(car);
        return "redirect:/biler";
    }
}
