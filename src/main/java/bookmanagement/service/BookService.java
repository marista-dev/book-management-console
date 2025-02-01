package bookmanagement.service;

import bookmanagement.domain.Book;
import bookmanagement.exception.BookNotFoundException;
import bookmanagement.exception.BooksAlreadyExistException;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {

  private final List<Book> books;

  public BookService(List<Book> books) {
    this.books = books;
  }

  public void addBook(Book book) {
    boolean exists = books.stream()
            .anyMatch(existingBook -> existingBook.getId().equals(book.getId()));
    if (exists) {
      throw new BooksAlreadyExistException("책이 이미 존재합니다 : " + book.getId());
    }
    books.add(book);
    System.out.println("책이 성공적으로 추가되었습니다 : " + book.getTitle());
  }

  public boolean deleteBook(String bookId) {
    boolean removed = books.removeIf(book -> book.getId().equals(bookId));
    if (removed) {
      System.out.println("책이 성공적으로 삭제되었습니다 : " + bookId);
    } else {
      throw new BookNotFoundException("책을 찾을 수 없습니다 : " + bookId);
    }
    return removed;
  }

  public List<Book> searchBooks(String keyword) {
    return books.stream()
            .filter(book -> book.getTitle().contains(keyword) || book.getAuthor().contains(keyword))
            .collect(Collectors.toList());
  }

  public void updateBook(String bookId, Book updatedBook) {
    Book existingBook = books.stream()
            .filter(book -> book.getId().equals(bookId))
            .findFirst()
            .orElseThrow(() -> new BookNotFoundException("책을 찾을 수 없습니다 : " + bookId));

    existingBook.setTitle(updatedBook.getTitle());
    existingBook.setAuthor(updatedBook.getAuthor());
    existingBook.setIsbn(updatedBook.getIsbn());
    existingBook.setCategories(updatedBook.getCategories());
    existingBook.setPrice(updatedBook.getPrice());
    existingBook.setStock(updatedBook.getStock());

    System.out.println("책이 성공적으로 업데이트되었습니다 : " + existingBook);
  }

}
