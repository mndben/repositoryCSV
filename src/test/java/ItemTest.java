import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {


    @Mock Item item;
    @Mock IOException ex;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("load csv data")
    void loadCsv() {
        String filePath = "./src/main/resources/Product.csv";

        /* Test : expected return type */
        assertInstanceOf(ArrayList.class, Item.loadCsv(filePath));

        /*Test : returned parsed csv */
        assertTrue(Item.loadCsv(filePath).size() > 0);
    }

    @Test @DisplayName("test for parseIntOrNull , and includes Test for parseFloatOrNull")
    void parseIntOrNull() {


        /* Test - returned int and double casting  */
        assertAll(() -> assertEquals(5, Item.parseIntOrNull("5")),
                () -> assertEquals(2, Item.parseIntOrNull("2")),
                () -> assertEquals(-1, Item.parseIntOrNull("3.0#adfa")),
                () -> assertEquals(-1, Item.parseIntOrNull("-1")));
        /* double casting */
        assertAll(() -> assertEquals(5, Item.parseFloatOrNull("5.0")),
                () -> assertEquals(2, Item.parseFloatOrNull("2.0")),
                () -> assertEquals(-1, Item.parseFloatOrNull("3afa")),
                () -> assertEquals(-1, Item.parseFloatOrNull("-1.0")));

        /* Test - with invalid int and double value  */
        assertDoesNotThrow(() -> { Item.parseIntOrNull("invalid"); });

    }

}