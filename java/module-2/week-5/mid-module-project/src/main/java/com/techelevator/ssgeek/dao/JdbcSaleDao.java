package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Sale;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcSaleDao implements SaleDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcSaleDao(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcSaleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Sale getSale(int saleId) {
        Sale sale = null;
        String sql = "SELECT sale_id, customer_id, sale_date, ship_date " +
                "FROM sale " +
                "WHERE sale_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,saleId);
        if (results.next()) {
            sale = mapRowToSale(results);
        }
        return sale;
    }

    @Override
    public List<Sale> getSalesUnshipped() {
        List<Sale> unshippedSales = new ArrayList<>();
        String sql = "SELECT sale_id, customer_id, sale_date, ship_date " +
                "FROM sale " +
                "WHERE ship_date IS NULL";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Sale sale = mapRowToSale(results);
            unshippedSales.add(sale);
        }
        return unshippedSales;
    }

    @Override
    public List<Sale> getSalesByCustomerId(int customerId) {
        List<Sale> customerSales = new ArrayList<>();
        String sql = "SELECT sale_id, customer_id, sale_date, ship_date " +
                "FROM sale " +
                "WHERE customer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Sale sale = mapRowToSale(results);
            customerSales.add(sale);
        }
        return customerSales;
    }

    @Override
    public List<Sale> getSalesByProductId(int productId) {
        List<Sale> productSales = new ArrayList<>();
        String sql = "SELECT sale_id, customer_id, sale_date, ship_date " +
                "FROM sale " +
                "JOIN line_item ON sale.sale_id = line_item.sale" +
                "WHERE customer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Sale sale = mapRowToSale(results);
            productSales.add(sale);
        }
        return productSales;
    }

    @Override
    public Sale createSale(Sale newSale) {
        String sql = "INSERT INTO sale (customer_name, sale_date, ship_date) " +
                "VALUES (?, ?, ? ) RETURNING sale_id;";
        int newId = jdbcTemplate.queryForObject(sql, int.class, newSale.getCustomerName(),
                newSale.getSaleDate(), newSale.getShipDate());
        return getSale(newId);
    }

    @Override
    public void updateSale(Sale updatedSale) {
        String sql = "UPDATE sale " +
                "SET customer_name = ?, sale_date = ?, ship_date = ? " +
                "WHERE sale_id = ?";
        jdbcTemplate.update(sql, updatedSale.getCustomerName(), updatedSale.getSaleDate(),
                updatedSale.getShipDate(),
                updatedSale.getSaleId());
    }

    @Override
    public void deleteSale(int saleId) {

    }
    private Sale mapRowToSale(SqlRowSet results) {
        Sale sale = new Sale();
        sale.setSaleId(results.getInt("sale_id"));
        sale.setCustomerId(results.getInt("customer_id"));
        sale.setSaleDate(results.getDate("sale_date").toLocalDate());
        sale.setShipDate(results.getDate("ship_date").toLocalDate());
        return sale;
    }

}
