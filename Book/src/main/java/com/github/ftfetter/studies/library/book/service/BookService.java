package com.github.ftfetter.studies.library.book.service;

import com.github.ftfetter.studies.library.book.entity.Book;
import com.github.ftfetter.studies.library.book.input.BookInput;
import com.github.ftfetter.studies.library.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

    public Book setBook(Book book) throws Exception {
        bookRepository.insert(book);
        return book;
    }

    public Book alterBook(String id, BookInput input) throws Exception {
        Book updatedBook = bookRepository.findById(id)
                .map(book -> mergeBook(book, input))
                .orElseThrow(() -> new ClassNotFoundException("Nenhum livro foi encontrado para alteração."));
        return bookRepository.update(updatedBook);
    }

    public Book deleteBook(String id) throws Exception {
        Book bookFound = bookRepository.findById(id)
                .orElseThrow(() -> new ClassNotFoundException("Nenhum livro foi encontrado para remoção."));
        return bookRepository.delete(bookFound);
    }

    private Book mergeBook(Book entity, BookInput input) {
        entity.setName(input.getName());
        entity.setAuthor(input.getAuthor());
        entity.setEdition(input.getEdition());

        return entity;
    }
}
