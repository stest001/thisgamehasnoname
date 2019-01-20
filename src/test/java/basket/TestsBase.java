package basket;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static base.BaseSteps.logIn;
import static base.BaseSteps.logOut;

public class TestsBase {

    @BeforeEach
    protected void init() {
        logIn();
    }

    @AfterEach
    protected void close() {
        logOut();

    }
}


