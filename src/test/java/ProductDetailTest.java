import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductDetailTest {

    ProductDetail productDetail = new ProductDetail();

    ArrayList<String[]> rawProduct = new ArrayList<>();


    @Test
    void processPortfolioProduct() {

        rawProduct.add(new String[]{"PTF1","P1","U11","EUR","10"});
        rawProduct.add(new String[]{"PTF1","P3","U36","CHF","20"});
        rawProduct.add(new String[]{"PTF2","X2","UX22","GPB","40"});
        productDetail.processPortfolioProduct(rawProduct);

        assertEquals("PTF1",productDetail.getProductDetailsObjectList().get("p1").getPortfolio());

    }

    @Test
    void productCount() {
        ProductDetail productDetailTest = new ProductDetail();
        productDetailTest.productCount();
        assertEquals(90,productDetailTest.getProductCount().get("p1"));
        assertEquals(100,productDetailTest.getProductCount().get("p2"));
        assertEquals(310,productDetailTest.getProductCount().get("p3"));
        assertEquals(70,productDetailTest.getProductCount().get("x1"));
        assertEquals(160,productDetailTest.getProductCount().get("x2"));
    }
}