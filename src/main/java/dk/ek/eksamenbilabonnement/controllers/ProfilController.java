package dk.ek.eksamenbilabonnement.controllers;

import dk.ek.eksamenbilabonnement.models.User;
import dk.ek.eksamenbilabonnement.services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfilController {

    private final AuthService authService;

    public ProfilController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/profil")
    public ModelAndView profil(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView mav = new ModelAndView("profil");
        mav.addObject("user", session.getAttribute("user"));

        return mav;
    }

    @PostMapping("/profil/opdater")
    public String updateProfile(
            HttpSession session,
            @RequestParam int id,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String role
    ) {

        User currentUser = (User) session.getAttribute("user");

        // keep old password if empty
        if (password == null || password.isEmpty()) {
            password = currentUser.getPassword();
        }

        User updatedUser = new User(
                id,
                email,
                password,
                firstName,
                lastName,
                role,
                null
        );

        authService.updateUser(updatedUser);

        // update session
        session.setAttribute("user", updatedUser);

        return "redirect:/profil";
    }
}