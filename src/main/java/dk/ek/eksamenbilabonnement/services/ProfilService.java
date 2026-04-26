package dk.ek.eksamenbilabonnement.services;

import dk.ek.eksamenbilabonnement.models.User;
import dk.ek.eksamenbilabonnement.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfilService {
    private final UserRepository userRepo;

    public ProfilService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
}
