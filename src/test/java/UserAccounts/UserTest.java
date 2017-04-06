package UserAccounts;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Chad on 4/4/2017.
 */
public class UserTest {

    //User user = new User("root", "wong", 9);
    User user = new User();

    @Test
    public void getUsername() {
        assertEquals("root", user.getUsername());
    }

    @Test
    public void setUsername() {
        user.setUsername("wwong");
        assertEquals("wwong", user.getUsername());
    }

    @Test
    public void getRealName() {
        assertEquals("wong", user.getRealName());
    }

    @Test
    public void setRealName() {
        user.setRealName("wilson");
        assertEquals("wilson", user.getRealName());
    }

    @Test
    public void getAuthenticationLevel() {
        assertEquals(9, user.getAuthenticationLevel());
    }

    @Test
    public void setAuthenticationLevel() {
        user.setAuthenticationLevel(4);
        assertEquals(4, user.getAuthenticationLevel());
    }

}