package com.devskiller.library.service;

import com.devskiller.library.exception.BookNotAvailableException;
import com.devskiller.library.exception.BookNotFoundException;
import com.devskiller.library.model.Book;
import com.devskiller.library.repository.BooksRepository;

import java.util.Optional;

public class BookService {
  private final BooksRepository booksRepository;

  public BookService(BooksRepository booksRepository) {
    this.booksRepository = booksRepository;
  }

  public void addBookCopy(Book book) {
    // Check if the book is already present in the repository
    Optional<Integer> existingCount = booksRepository.getBookCount(book);

    if (existingCount.isPresent()) {
      // If present, increment the count by one
      booksRepository.save(book, existingCount.get() + 1);
    } else {
      // If not present, save with a count of one
      booksRepository.save(book, 1);
    }
  }

  public void removeBookCopy(Book book) {
    // Check if the book is present in the repository
    Optional<Integer> existingCount = booksRepository.getBookCount(book);

    if (existingCount.isPresent()) {
      int currentCount = existingCount.get();
      if (currentCount > 0) {
        // Decrement the count by one
        booksRepository.save(book, currentCount - 1);
      } else {
        // Throw exception if book count is zero
        throw new BookNotAvailableException();
      }
    } else {
      // Throw exception if book is not found
      throw new BookNotFoundException();
    }
  }

  public int getAvailableBookCopies(Book book) {
    // Check if the book is present in the repository
    Optional<Integer> existingCount = booksRepository.getBookCount(book);

    if (existingCount.isPresent()) {
      int count = existingCount.get();
      if (count > 0) {
        return count; // Return the available count
      } else {
        throw new BookNotAvailableException(); // Count is zero
      }
    } else {
      throw new BookNotFoundException(); // Book is not found
    }
  }
}
