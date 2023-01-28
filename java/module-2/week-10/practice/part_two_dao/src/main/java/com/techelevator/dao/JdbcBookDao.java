package com.techelevator.dao;

import com.techelevator.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JdbcBookDao implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcBookDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Book getBook(int bookId) {
        Book book = null;
        String sql = "SELECT book_id" +
                "FROM book" +
                "WHERE book_id = ? :";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bookId);
        while (results.next()) {
            book = mapRowToBook(results);
        }
        return book;
    }

    @Override
    public Book updateRating(int bookId, BigDecimal newRating) {
        String sql = "UPDATE book" +
        "SET book_title = ?, star_rating = ?, out_of_print =? return null, foreword_by =?,publisher_id =?, published_date =?" +
    "WHERE book_id =?";
        jdbcTemplate.update(sql, bookId);
        return null;
    }

    @Override
    public void deleteBook(int bookId) {
        String deleteBook= "DELETE FROM book WHERE book_id = ?";
        jdbcTemplate.update(deleteBook, bookId);
    }

    private Book mapRowToBook(SqlRowSet results) {
        Book book = new Book();
        book.setBookId(results.getInt("book_id"));
        book.setBookTitle(results.getString("book_title"));
        book.setStarRating(results.getBigDecimal("star_rating"));
        book.setOutOfPrint(results.getBoolean("out_of_print"));
        book.setForewordBy((Integer) results.getObject("foreword_by"));
        book.setPublisherId(results.getInt("publisher_id"));
        book.setPublishedDate(results.getDate("published_date").toLocalDate());
        return book;
    }
}
