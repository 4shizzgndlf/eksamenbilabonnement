package dk.ek.eksamenbilabonnement.controllers;

import dk.ek.eksamenbilabonnement.models.Booking;
import dk.ek.eksamenbilabonnement.services.IndexService;
import dk.ek.eksamenbilabonnement.services.LejeaftaleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@Controller
public class LejeaftalerController {
    private final LejeaftaleService lejeaftaleService;

    public LejeaftalerController(LejeaftaleService lejeaftaleService) {
        this.lejeaftaleService = lejeaftaleService;
    }

    @GetMapping("/lejeaftaler")
    public ModelAndView lejeaftaler() {
        ModelAndView mav = new ModelAndView("lejeaftaler");

        mav.addObject("activeBookings", lejeaftaleService.getActiveBookings());
        mav.addObject("finishedBookings", lejeaftaleService.getFinishedBookings());

        return mav;
    }

    @PostMapping("/lejeaftaler/opret")
    public String createBooking(
            @RequestParam String customerName,
            @RequestParam int carId,
            @RequestParam(required = false) Integer userId,
            @RequestParam String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam double monthlyPrice,
            @RequestParam String status
    ) {

            Booking booking = new Booking(
                0,
                carId,
                userId != null ? userId : 0,
                customerName,
                Date.valueOf(startDate),
                endDate != null && !endDate.isEmpty() ? Date.valueOf(endDate) : null,
                monthlyPrice,
                status
        );

        lejeaftaleService.createBooking(booking);

        return "redirect:/lejeaftaler";
    }

    @PostMapping("/lejeaftaler/rediger")
    public String updateBooking(
            @RequestParam int id,
            @RequestParam String customerName,
            @RequestParam int carId,
            @RequestParam(required = false) Integer userId,
            @RequestParam String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam double monthlyPrice,
            @RequestParam String status
    ) {

        Booking booking = new Booking(
                id,
                carId,
                userId != null ? userId : 0,
                customerName,
                Date.valueOf(startDate),
                endDate != null && !endDate.isEmpty() ? Date.valueOf(endDate) : null,
                monthlyPrice,
                status
        );

        lejeaftaleService.updateBooking(booking);

        return "redirect:/lejeaftaler";
    }
}
