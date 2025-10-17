package com.seye.ProjectBookApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Create

    public Book createBook(Book book) {
        book.setBookId(null); // new entity
        book.setCreatedAt(LocalDateTime.now()); // set timestamp
        return bookRepository.save(book);
    }


    // Read (Get All)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Read (Get by ID)
    public Optional<Book> getBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    // Update
    public Book updateBook(Long bookId, Book updatedBook) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));

        existingBook.setBookName(updatedBook.getBookName());
        existingBook.setAuthor(updatedBook.getAuthor());

        return bookRepository.save(existingBook); // Managed entity, safe update
    }

    // Delete
    public void deleteBook(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new RuntimeException("Book not found with ID: " + bookId);
        }
        bookRepository.deleteById(bookId);
    }
}
