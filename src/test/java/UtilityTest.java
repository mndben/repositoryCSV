import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    void parseIntOrNull() {
        assertAll(() -> assertEquals(4, Utility.parseIntOrNull("4")),
                () -> assertEquals(-4, Utility.parseIntOrNull("-4")),
                () -> assertEquals(2,Utility.parseIntOrNull("2")),
                () -> assertEquals(0, Utility.parseIntOrNull("0")));
    }

    @Test
    void parseFloatOrNull() {
    }

    @Test
    void currencyExchange() {
    }
}