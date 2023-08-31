package ru.springcourse.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.springcourse.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();

        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setDate(rs.getObject("date", LocalDate.class));
        return book;
    }
}