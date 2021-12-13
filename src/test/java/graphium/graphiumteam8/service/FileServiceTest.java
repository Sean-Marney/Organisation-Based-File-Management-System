package graphium.graphiumteam8.service;

import graphium.graphiumteam8.entity.File;
import graphium.graphiumteam8.entity.Role;
import graphium.graphiumteam8.entity.User;
import graphium.graphiumteam8.repository.FileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FileServiceTest extends FileService {

    public FileServiceTest() {
        super(Mockito.mock(FileRepository.class));
    }

    @Test
    void addViewTest() {
        final File file = new File();
        final User user = new User();

        user.setPassword("password");
        user.setUsername("test_username");
        user.setRole(Role.USER);

        file.setUser(user);
        file.setAccessType(File.FileAccessType.PUBLIC);
        file.setFileObject(new byte[]{});
        file.setViews(new ArrayList<>());

        final SecurityContext context = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(context);
        final Authentication auth = Mockito.mock(Authentication.class);

        Mockito.when(context.getAuthentication()).thenReturn(auth);
        Mockito.when(auth.getPrincipal()).thenReturn(user);

        addView(file);

        Assertions.assertEquals(user, file.getViews().get(0).getUser());
        Assertions.assertEquals(1, file.getViews().size());

        addView(file);

        Assertions.assertEquals(user, file.getViews().get(0).getUser());
        Assertions.assertEquals(2, file.getViews().size());

    }

}