package UserAccounts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Chad on 4/4/2017.
 */
class AuthenticationTest {

    Authentication auth = new Authentication("root", "admin");

    @Test
    void getRealName() {
        assertEquals(null, auth.getRealName());
    }

    @Test
    void setRealName() {
        auth.setRealName("Jenny");
        assertEquals("Jenny", auth.getRealName());
    }

    @Test
    void getUsername() {
        assertEquals("root", auth.getUsername());
    }

    @Test
    void setUsername() {
        auth.setUsername("wwong");
        assertEquals("wwong", auth.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("admin", auth.getPassword());
    }

    @Test
    void setPassword() {
        auth.setPassword("ballerstatus");
        assertEquals("ballerstatus", auth.getPassword());
    }

    @Test
    void getValid() {
        assertEquals(false, auth.getValid());
    }

    @Test
    void setValid() {
        auth.setValid(true);
        assertEquals(true, auth.getValid());
    }

    @Test
    void getAuthentic() {
        assertEquals(false, auth.getAuthentic());
    }

    @Test
    void setAuthentic() {
        auth.setAuthentic(true);
        assertEquals(true, auth.getAuthentic());
    }

    @Test
    void getAuthenticationLevel() {
        assertEquals(0, auth.getAuthenticationLevel());
    }

    @Test
    void setAuthenticationLevel() {
        auth.setAuthenticationLevel(4);
        assertEquals(4, auth.getAuthenticationLevel());
    }

    @Test
    void authenticate() {
        assertEquals(true, auth.authenticate());
    }

}