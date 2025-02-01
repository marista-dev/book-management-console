package bookmanagement.service;

import bookmanagement.domain.Book;
import bookmanagement.domain.User;
import bookmanagement.exception.BookNotFoundException;
import bookmanagement.exception.InsufficientStockException;
import bookmanagement.exception.UserNotFoundException;
import java.util.List;

public class UserService {

  private List<User> users;
  private List<Book> books;

  public UserService(List<User> users, List<Book> books) {
    this.users = users;
    this.books = books;
  }

  // 도서 대출
  public void loanBook(String userId, String bookId) {
    User user = findUserById(userId);
    Book book = findBookById(bookId);
    // 도서 대출 가능 여부 확인
    if (book.getStock() <= 0) {
      throw new InsufficientStockException("재고가 부족합니다: " + book.getTitle());
    }
    if (user.getLoanedBooks().contains(book)) {
      throw new RuntimeException("이미 대출한 책입니다: " + book.getTitle());
    }

    // 도서 대출
    user.getLoanedBooks().add(book);
    book.setStock(book.getStock() - 1);
    System.out.println(user.getName() + "님이 " + book.getTitle() + "을 대출하였습니다.");
  }

  // 도서 반납
  public void returnBook(String userId, String bookId) {
    User user = findUserById(userId);
    Book book = findBookById(bookId);

    // 도서 반납 확인
    if (!user.getLoanedBooks().contains(book)) {
      throw new RuntimeException("대출한 적 없는 책입니다: " + book.getTitle());
    }

    // 도서 반납
    user.getLoanedBooks().remove(book);
    book.setStock(book.getStock() + 1);
    System.out.println(user.getName() + "님이 " + book.getTitle() + "을 반납하였습니다.");
  }

  // 대출 기록 조회
  public void getUserLoanHistory(String userId) {
    User user = findUserById(userId);
    System.out.println(user.getName() + "님의 대출 기록:");
    if (user.getLoanedBooks().isEmpty()) {
      System.out.println("대출한 책이 없습니다.");
    } else {
      user.getLoanedBooks().forEach(book ->
              System.out.println("- " + book.getTitle() + " (저자: " + book.getAuthor() + ")"));
    }
  }

  // 사용자 ID로 사용자 찾기
  private User findUserById(String userId) {
    return users.stream()
            .filter(user -> user.getId().equals(userId))
            .findFirst()
            .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다: " + userId));
  }

  // 책 ID로 책 찾기
  private Book findBookById(String bookId) {
    return books.stream()
            .filter(book -> book.getId().equals(bookId))
            .findFirst()
            .orElseThrow(() -> new BookNotFoundException("책을 찾을 수 없습니다: " + bookId));
  }
}
