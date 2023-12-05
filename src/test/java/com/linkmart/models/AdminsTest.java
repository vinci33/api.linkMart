package com.linkmart.models;
import de.huxhorn.sulky.ulid.ULID;
import com.linkmart.models.Admins;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdminsTest {
    @Test
    public void testSetId() {
        Admins admins = new Admins();
        ULID ulid = new ULID();
        String newId = ulid.nextULID();
        System.out.println("The generated ULID is: " + newId);
        System.out.println("The generated ULID from admins is: " + admins.getId());
        admins.setId(newId);
        assertEquals(newId, admins.getId());
    }
    @Test
    public void testCreateAdmin() {
        Admins admins = new Admins();
        String newId = admins.getId();
        System.out.println("The generated ULID is: " + newId);

        assertNotNull(newId);
    }
}