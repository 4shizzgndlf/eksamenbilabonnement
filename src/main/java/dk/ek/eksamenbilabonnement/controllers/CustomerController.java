    package dk.ek.eksamenbilabonnement.controllers;

    import dk.ek.eksamenbilabonnement.models.Customer;
    import dk.ek.eksamenbilabonnement.services.CustomerService;
    import jakarta.servlet.http.HttpSession;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.servlet.ModelAndView;

    import java.util.List;

    @Controller
    public class CustomerController {

        private final CustomerService customerService;

        public CustomerController(CustomerService customerService) {
            this.customerService = customerService;
        }

        // =========================
        // LIST ALL CUSTOMERS
        // =========================
        @GetMapping("/kunder")
        public ModelAndView customers(HttpSession session) {

            if (session.getAttribute("user") == null) {
                return new ModelAndView("redirect:/login");
            }

            ModelAndView mav = new ModelAndView("kunder");

            List<Customer> customers = customerService.getAllCustomers();

            mav.addObject("customers", customers);

            return mav;
        }

        // =========================
        // CREATE CUSTOMER
        // =========================
        @PostMapping("/kunder/opret")
        public String createCustomer(Customer customer, HttpSession session) {

            if (session.getAttribute("user") == null) {
                return "redirect:/login";
            }

            customerService.createCustomer(customer);

            return "redirect:/kunder";
        }

        // =========================
        // UPDATE CUSTOMER
        // =========================
        @PostMapping("/kunder/rediger")
        public String updateCustomer(Customer customer, HttpSession session) {

            if (session.getAttribute("user") == null) {
                return "redirect:/login";
            }

            customerService.updateCustomer(customer);

            return "redirect:/kunder";
        }

        // =========================
        // DELETE CUSTOMER
        // =========================
        @GetMapping("/kunder/slet")
        public String deleteCustomer(int id, HttpSession session) {

            if (session.getAttribute("user") == null) {
                return "redirect:/login";
            }

            customerService.deleteCustomer(id);

            return "redirect:/kunder";
        }
    }