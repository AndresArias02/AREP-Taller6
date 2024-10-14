import edu.eci.arep.models.Properties;
import edu.eci.arep.models.User;
import edu.eci.arep.repositorys.PropertiesRepository;
import edu.eci.arep.repositorys.UserRepository;
import edu.eci.arep.services.PropertiesService;
import edu.eci.arep.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for PropertiesService and UserService
 * Author: Andrés Arias
 */
class PropertiesTest {

    @Mock
    private PropertiesRepository propertiesRepository;

    @Mock
    private UserRepository userRepository;  // Mock para UserRepository

    @InjectMocks
    private PropertiesService propertiesService;

    @InjectMocks
    private UserService userService;  // Instancia de UserService

    private Properties properties;
    private User user;

    /**
     * Sets up the mocks and the Properties and User objects
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        properties = new Properties(1L, "123 Main St", "10.00", "Large", "Food delivery");
        user = new User(1L, "John", "Doe", "john.doe@example.com", "password");
    }

    /**
     * Tests the getAllProperties method
     */
    @Test
    void testGetAllProperties() {
        when(propertiesRepository.findAll()).thenReturn(Arrays.asList(properties));

        var propertiesList = propertiesService.getAllProperties();

        assertEquals(1, propertiesList.size());
        assertEquals(properties, propertiesList.get(0));
    }

    /**
     * Tests the getPropertiesById method
     */
    @Test
    void testGetPropertiesById() {
        when(propertiesRepository.findById(1L)).thenReturn(Optional.of(properties));

        Properties foundProperties = propertiesService.getPropertyById(1L);

        assertNotNull(foundProperties);
        assertEquals(properties.getAddress(), foundProperties.getAddress());
    }

    /**
     * Tests the addProperties method
     */
    @Test
    void testAddProperties() {
        propertiesService.addProperty(properties);

        verify(propertiesRepository, times(1)).save(properties);
    }

    /**
     * Tests the updateProperties method
     */
    @Test
    public void testUpdateProperties() {
        when(propertiesRepository.findById(1L)).thenReturn(Optional.of(properties));

        Properties updatedProperties = new Properties(1L, "456 Elm St", "15.00", "Medium", "Food delivery");
        propertiesService.updateProperty(1L, updatedProperties);

        // Validate the original properties are unchanged
        assertEquals("123 Main St", properties.getAddress());
        assertEquals("10.00", properties.getPrice());
        assertEquals("Large", properties.getSize());
        assertEquals("Food delivery", properties.getDescription());
    }

    /**
     * Tests the deleteProperties method
     */
    @Test
    void testDeleteProperties() {
        propertiesService.deleteProperty(1L);

        verify(propertiesRepository, times(1)).deleteById(1L);
    }

    /**
     * Tests the login method
     */
    @Test
    void testLogin() throws NoSuchAlgorithmException {
        // Set up the mock behavior
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(new User(1L, "John", "Doe", user.getEmail(), UserService.generatePasswordHash(user.getPassword())));

        // Perform the login
        boolean loginResult = userService.login(user);

        // Verify that login is successful
        assertTrue(loginResult);
    }


    /**
     * Tests the register method
     */
    @Test
    void testRegister() throws NoSuchAlgorithmException {
        // Set up the mock behavior
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);

        // Perform the registration
        boolean registrationResult = userService.register(user);

        // Verify that registration is successful
        assertTrue(registrationResult);
        verify(userRepository, times(1)).save(user);  // Verify save is called
    }
}
