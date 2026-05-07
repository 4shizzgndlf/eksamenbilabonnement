package dk.ek.eksamenbilabonnement.services;

import dk.ek.eksamenbilabonnement.models.User;
import dk.ek.eksamenbilabonnement.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {
    private final UserRepository userRepo;

    public DashboardService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Map<String, Integer> getRoleCounts() {

        List<User> users = userRepo.findAll();

        Map<String, Integer> roleCounts = new HashMap<>();

        for (User user : users) {

            String role = user.getRole();

            if (roleCounts.get(role) == null) {
                roleCounts.put(role, 1);
            } else {
                roleCounts.put(role, roleCounts.get(role) + 1);
            }
        }

        return roleCounts;
    }

    public Map<String, Integer> getDuplicateNameCounts() {

        List<User> users = userRepo.findAll();

        Map<String, Integer> nameCounts = new HashMap<>();

        for (User user : users) {

            String fullName =
                    (user.getFirstName().trim() + " " + user.getLastName().trim());

            if (nameCounts.get(fullName) == null) {
                nameCounts.put(fullName, 1);
            } else {
                nameCounts.put(fullName, nameCounts.get(fullName) + 1);
            }
        }

        Map<String, Integer> duplicatesOnly = new HashMap<>();

        for (String key : nameCounts.keySet()) {
            if (nameCounts.get(key) >= 2) {
                duplicatesOnly.put(key, nameCounts.get(key));
            }
        }

        return duplicatesOnly;
    }
}
