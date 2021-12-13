import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {


    @Test
    void main() {

        /*object instance */
        Client client = new Client();
        Product product  = new Product();
        PortFolio portFolio = new PortFolio();

        /* testing main is function as expected */

        product.setName("productY");
        product.setPrice(10023.00);

        System.out.println("product name: " +product.getName() + "\n" +
                "product Price: "+product.getPrice());

        /* testing main args feature */
        String [] args = { "one", "two", "three" };
        Main.main(args);



    }
}