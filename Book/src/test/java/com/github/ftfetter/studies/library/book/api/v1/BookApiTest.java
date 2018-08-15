package com.github.ftfetter.studies.library.book.api.v1;

import com.github.ftfetter.studies.library.book.entity.Book;
import com.github.ftfetter.studies.library.book.exception.ExpectationFailedException;
import com.github.ftfetter.studies.library.book.exception.InternalServerErrorException;
import com.github.ftfetter.studies.library.book.input.BookInput;
import com.github.ftfetter.studies.library.book.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookApiTest {

    private BookApi bookApi;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = mock(BookService.class);
        bookApi = new BookApi(bookService);
    }

    @Test
    void getAllBooksTestException() throws Exception {
        when(bookService.getAllBooks())
                .thenThrow(new Exception("exception test"));

        InternalServerErrorException thrown = assertThrows(
                InternalServerErrorException.class,
                () -> bookApi.getAllBooks()
        );
        assertEquals("exception test", thrown.getMessage());
    }

    @Test
    void getBookByIdTestNoContent() throws Exception {
        when(bookService.getBookById(anyString()))
                .thenReturn(buildEmpty());

        ResponseEntity response = bookApi.getBookById("");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getBookByIdTestException() throws Exception {
        when(bookService.getBookById(anyString()))
                .thenThrow(new Exception("exception test"));

        InternalServerErrorException thrown = assertThrows(
                InternalServerErrorException.class,
                () -> bookApi.getBookById("")
        );
        assertEquals("exception test", thrown.getMessage());
    }

    @Test
    void saveBookTestException() throws Exception {
        when(bookService.saveBook(any(Book.class)))
                .thenThrow(new Exception("exception test"));

        InternalServerErrorException thrown = assertThrows(
                InternalServerErrorException.class,
                () -> bookApi.saveBook(buildBookInput())
        );
        assertEquals("exception test", thrown.getMessage());
    }

    @Test
    void alterBookTestExpectetionFailed() throws Exception {
        when(bookService.updateBook(anyString(), any(BookInput.class)))
                .thenThrow(new ExpectationFailedException("exception test"));

        ExpectationFailedException thrown = assertThrows(
                ExpectationFailedException.class,
                () -> bookApi.updateBook("", buildBookInput())
        );
        assertEquals("exception test", thrown.getMessage());
    }

    @Test
    void alterBookTestException() throws Exception {
        when(bookService.updateBook(anyString(), any(BookInput.class)))
                .thenThrow(new Exception("exception test"));

        Exception thrown = assertThrows(
                Exception.class,
                () -> bookApi.updateBook("", buildBookInput())
        );
        assertEquals("exception test", thrown.getMessage());
    }

    @Test
    void deleteBookTestNoContent() throws Exception {
        when(bookService.deleteBook(anyString()))
                .thenReturn(buildBook());

        ResponseEntity response = bookApi.deleteBook("");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteBookTestExpectationFailed() throws Exception {
        when(bookService.deleteBook(anyString()))
                .thenThrow(new ExpectationFailedException("exception test"));

        ExpectationFailedException thrown = assertThrows(
                ExpectationFailedException.class,
                () -> bookApi.deleteBook("")
        );
        assertEquals("exception test", thrown.getMessage());
    }

    @Test
    void deleteBookTestException() throws Exception {
        when(bookService.deleteBook(anyString()))
                .thenThrow(new Exception("exception test"));

        Exception thrown = assertThrows(
                Exception.class,
                () -> bookApi.deleteBook("")
        );
        assertEquals("exception test", thrown.getMessage());
    }

    private Optional<Book> buildEmpty() {
        return Optional.empty();
    }

    private Book buildBook() {
        return new Book("", "", 1, LocalDate.now());
    }

    private BookInput buildBookInput() {
        return new BookInput();
    }
}