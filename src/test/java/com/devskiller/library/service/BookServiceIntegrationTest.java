package com.devskiller.library.service;

import com.devskiller.library.exception.BookNotAvailableException;
import com.devskiller.library.exception.BookNotFoundException;
import com.devskiller.library.model.Book;
import com.devskiller.library.repository.BooksRepository;
import com.devskiller.library.repository.MockBooksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookServiceIntegrationTest {
  private BooksRepository booksRepository;
  private BookService bookService;

  @BeforeEach
  void setUp() {
    booksRepository = new MockBooksRepository();
    bookService = new BookService(booksRepository);
  }

  @Test
  void addedBookCopyIsAvailable() {
    Book book = testBook();

    bookService.addBookCopy(book);

    assertEquals(1, booksRepository.getDistinctBooksCount());
    assertEquals(1, booksRepository.getBookCount(book).get());
  }

  @Test
  void addedBookCopiesAreSummed() {
    Book book = testBook();

    bookService.addBookCopy(book);
    bookService.addBookCopy(book);

    assertEquals(1, booksRepository.getDistinctBooksCount());
    assertEquals(2, booksRepository.getBookCount(book).get());
  }

  @Test
  void removeBookCopyRemovesSingleCopy() {
    Book book = testBook();
    booksRepository.save(book, 2);

    bookService.removeBookCopy(book);

    assertEquals(1, booksRepository.getDistinctBooksCount());
    assertEquals(1, booksRepository.getBookCount(book).get());
  }

  @Test
  void removeBookCopyThrowsExceptionWhenBookNotAvailable() {
    Book book = testBook();
    booksRepository.save(book, 0);

    assertThrows(BookNotAvailableException.class, () -> bookService.removeBookCopy(book));
    assertEquals(1, booksRepository.getDistinctBooksCount());
    assertEquals(0, booksRepository.getBookCount(book).get());
  }

  @Test
  void removeBookCopyThrowsExceptionWhenBookNotFound() {
    Book book = testBook();

    assertThrows(BookNotFoundException.class, () -> bookService.removeBookCopy(book));
    assertEquals(0, booksRepository.getDistinctBooksCount());
  }

  @Test
  void getAvailableBookCopies() {
    Book book = testBook();
    booksRepository.save(book, 2);

    assertEquals(2, bookService.getAvailableBookCopies(book));
    assertEquals(1, booksRepository.getDistinctBooksCount());
    assertEquals(2, booksRepository.getBookCount(book).get());
  }

  @Test
  void getAvailableBookCopiesThrowsExceptionWhenBookNotFound() {
    Book book = testBook();

    assertThrows(BookNotFoundException.class, () -> bookService.getAvailableBookCopies(book));
    assertEquals(0, booksRepository.getDistinctBooksCount());
  }

  @Test
  void getAvailableBookCopiesThrowsExceptionWhenBookNotAvailable() {
    Book book = testBook();
    booksRepository.save(book, 0);

    assertThrows(BookNotAvailableException.class, () -> bookService.getAvailableBookCopies(book));
    assertEquals(1, booksRepository.getDistinctBooksCount());
    assertEquals(0, booksRepository.getBookCount(book).get());
  }

  static Book testBook() {
    return new Book(9781260440218L);
  }

}