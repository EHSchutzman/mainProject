package UserAccounts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Chad on 4/4/2017.
 */
class UserTest {

    User user = new User("root", "wong", 9);

    @Test
    void getUsername() {
        assertEquals("root", user.getUsername());
    }

    @Test
    void setUsername() {
        user.setUsername("wwong");
        assertEquals("wwong", user.getUsername());
    }

    @Test
    void getRealName() {
        assertEquals("wong", user.getRealName());
    }

    @Test
    void setRealName() {
        user.setRealName("wilson");
        assertEquals("wilson", user.getRealName());
    }

    @Test
    void getAuthenticationLevel() {
        assertEquals(9, user.getAuthenticationLevel());
    }

    @Test
    void setAuthenticationLevel() {
        user.setAuthenticationLevel(4);
        assertEquals(4, user.getAuthenticationLevel());
    }

}