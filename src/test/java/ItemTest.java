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
}