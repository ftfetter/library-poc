package com.github.ftfetter.studies.library.book.entity.mapper;

import com.github.ftfetter.studies.library.book.entity.Book;
import com.github.ftfetter.studies.library.book.input.BookInput;

import java.time.LocalDate;

public class BookMapper {

    public static Book map(BookInput input) {
        return new Book(input.getName(), input.getAuthor(), input.getEdition(), LocalDate.now());
    }
}
