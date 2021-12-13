package graphium.graphiumteam8.controller;

import graphium.graphiumteam8.entity.Role;
import graphium.graphiumteam8.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Test
    public void userValidationTest() {
        final LoginController loginController = new LoginController();
        final User user = new User();

        user.setPassword("password");
        user.setUsername("test_username");
        user.setRole(Role.USER);

        final SecurityContext context = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(context);
        final Authentication auth = Mockito.mock(Authentication.class);

        Mockito.when(context.getAuthentication()).thenReturn(auth);
        Mockito.when(auth.getPrincipal()).thenReturn(user);

        assertEquals("redirect:/user", loginController.loginSuccess());
    }

    @Test
    public void organisationValidationTest() {
        final LoginController loginController = new LoginController();
        final User user = new User();

        user.setPassword("password");
        user.setUsername("test_username");
        user.setRole(Role.ORGANISATION);

        final SecurityContext context = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(context);
        final Authentication auth = Mockito.mock(Authentication.class);

        Mockito.when(context.getAuthentication()).thenReturn(auth);
        Mockito.when(auth.getPrincipal()).thenReturn(user);

        assertEquals("redirect:/organisation", loginController.loginSuccess());
    }

    @Test
    public void adminValidationTest() {
        final LoginController loginController = new LoginController();
        final User user = new User();

        user.setPassword("password");
        user.setUsername("test_username");
        user.setRole(Role.ADMIN);

        final SecurityContext context = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(context);
        final Authentication auth = Mockito.mock(Authentication.class);

        Mockito.when(context.getAuthentication()).thenReturn(auth);
        Mockito.when(auth.getPrincipal()).thenReturn(user);

        assertEquals("redirect:/management", loginController.loginSuccess());
    }

}