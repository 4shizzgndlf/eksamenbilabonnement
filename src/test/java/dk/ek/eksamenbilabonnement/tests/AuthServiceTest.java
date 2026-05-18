package dk.ek.eksamenbilabonnement.tests;

import dk.ek.eksamenbilabonnement.models.User;
import dk.ek.eksamenbilabonnement.repositories.UserRepository;
import dk.ek.eksamenbilabonnement.repositories.UserRepositoryImpl;
import dk.ek.eksamenbilabonnement.services.AuthService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthServiceTest {
    // Simple fake repository (no Mockito)
    static class FakeUserRepository extends UserRepositoryImpl {
        @Override
        public User findByEmail(String email) {

            if (email.equals("test@test.com")) {
                return new User(
                        1,
                        "test@test.com",
                        "1234",
                        "test",
                        "user",
                        "admin",
                        "2026-04-24 17:06:01"
                );
            }

            return null;
        }

        User savedUser;
        @Override
        public void createUser(User user) {
            this.savedUser = user;
        }
    }

    @Test
    void canLogIn() {

        // Arrange
        FakeUserRepository fakeRepo = new FakeUserRepository();
        AuthService authService = new AuthService(fakeRepo);

        // Act
        User user = authService.login("test@test.com", "1234");

        // Assert
        assertEquals("1234", user.getPassword());
    }

    @Test
    void canRegisterUser() {
        // Arrange
        FakeUserRepository fakeRepo = new FakeUserRepository();
        AuthService authService = new AuthService(fakeRepo);

        User newUser = new User(
                1,
                "newuser@test.com",
                "1234",
                "Emil",
                "Kristensen",
                "admin",
                "2026-04-24 17:06:01"
        );

        // Act
        authService.register(newUser);

        // Assert
        assertEquals("newuser@test.com", fakeRepo.savedUser.getEmail());
        assertEquals("1234", fakeRepo.savedUser.getPassword());
        assertEquals("Emil", fakeRepo.savedUser.getFirstName());
    }
}
