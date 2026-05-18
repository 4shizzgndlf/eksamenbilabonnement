package dk.ek.eksamenbilabonnement.controllers;

import dk.ek.eksamenbilabonnement.models.User;
import dk.ek.eksamenbilabonnement.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DashboardController {
    private final DashboardService dashboardService;
    private final LejeaftaleService lejeaftaleService;
    private final SkadeService skadeService;
    private final BilerService bilerService;

    public DashboardController(DashboardService dashboardService, LejeaftaleService lejeaftaleService, SkadeService skadeService, BilerService bilerService) {
        this.dashboardService = dashboardService;
        this.lejeaftaleService = lejeaftaleService;
        this.skadeService = skadeService;
        this.bilerService = bilerService;
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView mav = new ModelAndView("dashboard");

        List<User> users = dashboardService.getAllUsers();

        mav.addObject("users", dashboardService.getAllUsers());
        mav.addObject("userCount", users.size());
        mav.addObject("rolesCount", dashboardService.getRoleCounts());
        mav.addObject("duplicateNames", dashboardService.getDuplicateNameCounts());
        mav.addObject("bookingsCount", lejeaftaleService.getAllBookingsCount());
        mav.addObject("rentedCars", lejeaftaleService.getRentedCarIds());
        mav.addObject("bookingsPerUser", lejeaftaleService.getBookingsPerUser());
        mav.addObject("activeBookingsCount", lejeaftaleService.getActiveBookingsCount());
        mav.addObject("finishedBookingsCount", lejeaftaleService.getFinishedBookingsCount());
        mav.addObject("averageMonthlyPrice", lejeaftaleService.getAverageMonthlyPrice());
        mav.addObject("avgRentalDays", lejeaftaleService.getAverageRentalDays());
        mav.addObject("damageCount", skadeService.getDamageCount());
        mav.addObject("damagesPerCar", skadeService.getDamagesPerCar());
        mav.addObject("averageDamagePrice", skadeService.getAverageDamagePrice());
        mav.addObject("damagePricePerCar", skadeService.getTotalDamagePricePerCar());
        mav.addObject("carCount", bilerService.getCarCount());
        mav.addObject("availableCars", bilerService.getAvailableCarCount());
        mav.addObject("rentedCarCount", bilerService.getRentedCarCount());
        mav.addObject("carsWithDamage", bilerService.getCarsWithDamageCount());
        mav.addObject("carsForSale", bilerService.getCarsByStatus("KLAR_TIL_SALG"));
        mav.addObject("soldCarsStats", bilerService.getSoldCarsByBrandAndModel());
        mav.addObject("returnedCarsCount", bilerService.getReturnedCarsCount());
        mav.addObject("averageMonthlyPrice", bilerService.getAverageMonthlyPrice());
        mav.addObject("averagePurchasePrice", bilerService.getAveragePurchasePrice());
        mav.addObject("averageRentedMonthlyPrice", bilerService.getAverageRentedCarMonthlyPrice());
        mav.addObject("totalRentedMonthlyPrice", bilerService.getTotalRentedMonthlyPrice());
        mav.addObject("totalContractRevenue", lejeaftaleService.getTotalRevenueForFinishedContracts());
        return mav;
    }

    @PostMapping("/dashboard/delete-user")
    public String deleteUser(@RequestParam int id,
                             HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        dashboardService.deleteUser(id);

        return "redirect:/dashboard";
    }
}
