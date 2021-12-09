import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @org.junit.jupiter.api.Test
    void parseIntOrNull() {
        assertAll(() -> assertEquals(4, Utility.parseIntOrNull("4")),
                () -> assertEquals(-4, Utility.parseIntOrNull("-4")),
                () -> assertEquals(2,Utility.parseIntOrNull("2")),
                () -> assertEquals(-1,Utility.parseFloatOrNull("2b")),
                () -> assertEquals(0, Utility.parseIntOrNull("0")));
    }

    @org.junit.jupiter.api.Test
    void parseFloatOrNull() {

        assertAll(() -> assertEquals(4.00, Utility.parseFloatOrNull("4.00")),
                () -> assertEquals(-4.00, Utility.parseFloatOrNull("-4.00")),
                () -> assertEquals(2.00,Utility.parseFloatOrNull("2.00")),
                () -> assertEquals(-1.00,Utility.parseFloatOrNull("2b.00")),
                () -> assertEquals(0.00, Utility.parseFloatOrNull("0.00")));

    }

    @org.junit.jupiter.api.Test
    void currencyExchange() {

        assertAll(() -> assertEquals(4.00 / 2.00, Utility.currencyExchange(4.00,"USD")),
                () -> assertEquals(100.00 / 50.00, Utility.currencyExchange(100.00,"JPY")),
                () -> assertEquals(2.00 * 8.00,Utility.currencyExchange(2.00,"GPB")),
                () -> assertEquals(1.00*4.00, Utility.currencyExchange(1.00,"CHF")),
                () -> assertEquals(1.00/10.00, Utility.currencyExchange(1.00,"TND")));

    }
}