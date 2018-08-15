package com.github.ftfetter.studies.library.book.service;

import com.github.ftfetter.studies.library.book.entity.Book;
import com.github.ftfetter.studies.library.book.entity.mapper.BookMapper;
import com.github.ftfetter.studies.library.book.exception.ExpectationFailedException;
import com.github.ftfetter.studies.library.book.input.BookInput;
import com.github.ftfetter.studies.library.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() throws Exception {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(String id) throws Exception {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) throws Exception {
        return bookRepository.insert(book);
    }

    public Book updateBook(String id, BookInput input) throws Exception {
        Book updatedBook = bookRepository.findById(id)
                .map(book -> BookMapper.merge(book, input))
                .orElseThrow(() -> new ExpectationFailedException("Nenhum livro foi encontrado para alteração."));
        return bookRepository.update(updatedBook);
    }

    public Book deleteBook(String id) throws Exception {
        Book bookFound = bookRepository.findById(id)
                .orElseThrow(() -> new ExpectationFailedException("Nenhum livro foi encontrado para remoção."));
        return bookRepository.delete(bookFound);
    }
}
