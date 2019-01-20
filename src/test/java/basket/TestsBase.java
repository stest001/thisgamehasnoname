package basket;

import base.BaseSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static base.BaseSteps.logIn;

public class TestsBase {

    @BeforeEach
    protected void init() {
        logIn();
    }

    @AfterEach
    protected void close() {
        BaseSteps.close();
    }
}


