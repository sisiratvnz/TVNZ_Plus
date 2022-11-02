package nz.co.tvnz.tests;

import nz.co.tvnz.BaseTest;
import org.junit.jupiter.api.Test;
import nz.co.tvnz.tests.RegistrationAndLogin;

public class Shows extends BaseTest {
    @Test
    public void loadShow() {
        RegistrationAndLogin registrationAndLogin = new RegistrationAndLogin();
        registrationAndLogin.logIn();

    }
}
