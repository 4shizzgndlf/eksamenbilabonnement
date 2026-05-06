package dk.ek.eksamenbilabonnement.repositories;

import dk.ek.eksamenbilabonnement.models.User;
import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findByEmail(String email);

    void createUser(User user);

    void updateUser(User user);
}