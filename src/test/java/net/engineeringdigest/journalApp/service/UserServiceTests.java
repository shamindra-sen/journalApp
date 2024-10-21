package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindByUsername(){
        assertNotNull(userRepository.findByusername("sham"));
    }

    @ParameterizedTest
    @ValueSource(strings ={
            "Rai",
            "shamindra",
            "Sam",
            "sham"
    })
    public void testFindByUsername(String username){
        assertNotNull(userRepository.findByusername(username),"Tests failed for"+username);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,5,8",
            "7,9,87"
    })
    public void test(int a,int b, int expected){
        assertEquals(expected, a+b);
    }
}
