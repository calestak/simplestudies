package com.techelevator.ssgeek.dao;


import com.techelevator.ssgeek.model.Customer;
import com.techelevator.ssgeek.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class JdbcProductDaoTests extends BaseDaoTests {
    private static final Product PRODUCT_1 =new Product(1, "Product 1", "Description 1", BigDecimal.valueOf(10.00), "Picture 1");
    private static final Product PRODUCT_2 =new Product(2, "Product 2", "Description 2", BigDecimal.valueOf(10.00), "Picture 2");
    private static final Product PRODUCT_3 =new Product(3, "Product 3", "Description 3", BigDecimal.valueOf(10.00), "Picture 3");
    private static final Product PRODUCT_4 =new Product(4, "Product 4", "Description 4", BigDecimal.valueOf(10.00), "Picture 4");

    private JdbcProductDao productDao;
    private Product testProduct;

    @Before
    public void setup() {
        productDao = new JdbcProductDao(dataSource);
        testProduct = new Product(0, "Product_0", "Description 0", BigDecimal.valueOf(10.00), "'Picture 1" );

    }
    @Test
    public void getProduct_returns_correct_product_for_id() {
        Product product = productDao.getProduct(1);
        Assert.assertNotNull("getCustomer returned null", product);
        assertProductsMatch("getProduct returned wrong or partial data", PRODUCT_1, product);

        product = productDao.getProduct(4);
        Assert.assertNotNull("getProduct returned null", product);
        assertProductsMatch("getProduct returned wrong or partial data", PRODUCT_4, product);
    }

    private void assertProductsMatch(String s, Product product1, Product product) {
        String message = s + ": unexpected data in field '%s'.";
        Assert.assertEquals(String.format(message, "productId"), expected.getStreetAddress1(), actual.getStreetAddress1());
        Assert.assertEquals(String.format(message, "streetAddress2"), expected.getStreetAddress2(), actual.getStreetAddress2());
        Assert.assertEquals(String.format(message, "city"), expected.getCity(), actual.getCity());
        Assert.assertEquals(String.format(message, "state"), expected.getState(), actual.getState());
        Assert.assertEquals(String.format(message, "zipCode"), expected.getZipCode(), actual.getZipCode());
    }


}
