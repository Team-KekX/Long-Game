package eu.kekx.long_game.service;

import eu.kekx.long_game.domain.User;
import eu.kekx.long_game.persistence.user.UserRepository;
import eu.kekx.long_game.presentation.request.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    UserRepository mockUserRepository;
    UserService userService;

    @BeforeEach
    void setUp() {
        mockUserRepository = mock(UserRepository.class);
        userService = new UserService(mockUserRepository);
    }

    @Test
    void createUser_WhenRequestIsNull_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> userService.createUser(null));
    }

    @Test 
    void createUser_WhenUsernameIsNull_ThrowsNullPointerException() {
        var request = new UserRequest(null, "test@example.com", "password");
        
        assertThrows(NullPointerException.class, () -> userService.createUser(request));
    }

    @Test
    void createUser_WhenUsernameIsBlank_ThrowsIllegalArgumentException() {
        var request = new UserRequest("", "test@example.com", "password");
        
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(request));
    }

    @Test
    void createUser_WhenEmailIsNull_ThrowsNullPointerException() {
        var request = new UserRequest("username", null, "password");
        
        assertThrows(NullPointerException.class, () -> userService.createUser(request));
    }

    @Test
    void createUser_WhenEmailIsBlank_ThrowsIllegalArgumentException() {
        var request = new UserRequest("username", "", "password");
        
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(request));
    }

    @Test
    void createUser_WhenPasswordIsNull_ThrowsNullPointerException() {
        var request = new UserRequest("username", "test@example.com", null);
        
        assertThrows(NullPointerException.class, () -> userService.createUser(request));
    }

    @Test
    void createUser_WhenPasswordIsBlank_ThrowsIllegalArgumentException() {
        var request = new UserRequest("username", "test@example.com", "");
        
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(request));
    }

    @Test
    void createUser_WhenEmailExists_ThrowsIllegalArgumentException() {
        var request = new UserRequest("username", "test@example.com", "password");
        when(mockUserRepository.existsByEmail("test@example.com")).thenReturn(true);
        
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(request));
    }

    @Test
    void createUser_WhenUsernameExists_ThrowsIllegalArgumentException() {
        var request = new UserRequest("username", "test@example.com", "password");
        when(mockUserRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(mockUserRepository.existsByUsername("username")).thenReturn(true);
        
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(request));
    }

    @Test
    void createUser_WhenValidRequest_ReturnsCreatedUser() {
        var request = new UserRequest("username", "test@example.com", "password");
        when(mockUserRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(mockUserRepository.existsByUsername("username")).thenReturn(false);
        
        var expectedUser = new User("username", "test@example.com", "password");
        when(mockUserRepository.save(any(User.class))).thenReturn(expectedUser);
        
        var result = userService.createUser(request);
        
        assertNotNull(result);
        assertEquals("username", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("password", result.getPassword());
        
        verify(mockUserRepository).save(any(User.class));
    }

    
}
