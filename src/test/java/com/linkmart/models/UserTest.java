package com.linkmart.models;

import com.linkmart.models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class UserTest {
    @Test
    public void testCreateUser() {
        User user = new User();
        String newId = user.getId();
        System.out.println("The generated ULID is: " + newId);
        assertNotNull(newId);
    }
}
