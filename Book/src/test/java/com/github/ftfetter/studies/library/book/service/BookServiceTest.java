package com.github.ftfetter.studies.library.book.service;

import com.github.ftfetter.studies.library.book.entity.Book;
import com.github.ftfetter.studies.library.book.exception.ExpectationFailedException;
import com.github.ftfetter.studies.library.book.input.BookInput;
import com.github.ftfetter.studies.library.book.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceTest {

    private BookService bookService;
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void getAllBooksTest() throws Exception {
        List<Book> booksFound = buildBooks();

        when(bookRepository.findAll())
                .thenReturn(booksFound);

        assertIterableEquals(booksFound, bookService.getAllBooks());
    }

    @Test
    void getBookByIdTestBookFound() throws Exception {
        Optional<Book> bookFound = buildBookFound();

        when(bookRepository.findById(anyString()))
                .thenReturn(bookFound);

        assertEquals(bookFound, bookService.getBookById(""));
    }

    @Test
    void getBookByIdTestBookNotFound() throws Exception {
        Optional<Book> bookNotFound = buildEmpty();

        when(bookRepository.findById(anyString()))
                .thenReturn(bookNotFound);

        assertEquals(bookNotFound, bookService.getBookById(""));
    }

    @Test
    void saveBookTest() throws Exception {
        Book bookSaved = buildBook();

        when(bookRepository.insert(any(Book.class)))
                .thenReturn(bookSaved);

        assertEquals(bookSaved, bookService.saveBook(buildBook()));
    }

    @Test
    void updateBookTest() throws Exception {
        Book bookUpdated = buildBook();

        when(bookRepository.findById(anyString()))
                .thenReturn(buildBookFound());
        when(bookRepository.update(any(Book.class)))
                .thenReturn(bookUpdated);

        assertEquals(bookUpdated, bookService.updateBook("", buildBookInput()));
    }

    @Test
    void updateBookTestNotFound() throws Exception {
        when(bookRepository.findById(anyString()))
                .thenReturn(buildEmpty());

        ExpectationFailedException thrown = assertThrows(
                ExpectationFailedException.class,
                () -> bookService.updateBook("", buildBookInput())
        );
        assertEquals("Nenhum livro foi encontrado para alteração.", thrown.getMessage());
    }

    @Test
    void deleteBookTest() throws Exception {
        Book bookDeleted = buildBook();

        when(bookRepository.findById(anyString()))
                .thenReturn(buildBookFound());
        when(bookRepository.delete(any(Book.class)))
                .thenReturn(bookDeleted);

        assertEquals(bookDeleted, bookService.deleteBook(""));
    }

    @Test
    void deleteBookTestNotFound() throws Exception {
        when(bookRepository.findById(anyString()))
                .thenReturn(buildEmpty());

        ExpectationFailedException thrown = assertThrows(
                ExpectationFailedException.class,
                () -> bookService.deleteBook("")
        );
        assertEquals("Nenhum livro foi encontrado para remoção.", thrown.getMessage());
    }

    private Optional<Book> buildEmpty() {
        return Optional.empty();
    }

    private Optional<Book> buildBookFound() {
        return Optional.of(buildBook());
    }

    private List<Book> buildBooks() {
        return Arrays.asList(buildBook());
    }

    private Book buildBook() {
        return new Book("test", "test", 1, LocalDate.now());
    }

    private BookInput buildBookInput() {
        return new BookInput();
    }
}