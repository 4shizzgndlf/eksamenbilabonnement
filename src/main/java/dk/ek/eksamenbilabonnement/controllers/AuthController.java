package dk.ek.eksamenbilabonnement.controllers;

import dk.ek.eksamenbilabonnement.models.User;
import dk.ek.eksamenbilabonnement.services.AuthService;
import dk.ek.eksamenbilabonnement.services.IndexService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ===== LOGIN PAGE =====
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // ===== LOGIN ACTION =====
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {

        User user = authService.login(email, password);

        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        }

        return "redirect:/login?error";
    }

    // ===== REGISTER PAGE =====
    @GetMapping("/registrer")
    public String registerPage() {
        return "registrer";
    }

    // ===== REGISTER ACTION =====
    @PostMapping("/registrer")
    public String register(@RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String role) {

        User user = new User(0, email, password, firstName, lastName, role, null);
        authService.register(user);

        return "redirect:/login";
    }

    // ===== LOGOUT =====
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}