package bookmanagement.ui;

import bookmanagement.domain.Book;
import bookmanagement.exception.BookNotFoundException;
import bookmanagement.exception.InsufficientStockException;
import bookmanagement.exception.UserNotFoundException;
import bookmanagement.service.BookService;
import bookmanagement.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryController {

  private final BookService bookService;
  private final UserService userService;
  private final Scanner scanner;

  public LibraryController(BookService bookService, UserService userService) {
    this.bookService = bookService;
    this.userService = userService;
    this.scanner = new Scanner(System.in);
  }

  /*
   * 프로그램 실행 (메뉴 표시 및 입력 처리)
   */
  public void run() {
    while (true) {
      System.out.println("\n===📚도서 관리 시스템 ===");
      System.out.println("1. 도서 추가");
      System.out.println("2. 도서 삭제");
      System.out.println("3. 도서 목록 조회");
      System.out.println("4. 도서 수정");
      System.out.println("5. 도서 대출");
      System.out.println("6. 도서 반납");
      System.out.println("7. 대출 기록 조회");
      System.out.println("0. 종료");
      System.out.print("메뉴를 선택하세요: ");

      String choice = scanner.nextLine();
      switch (choice) {
        case "1" -> handleAddBook();
        case "2" -> handleDeleteBook();
        case "3" -> handleSearchBook();
        case "4" -> handleUpdateBook();
        case "5" -> handleLoanBook();
        case "6" -> handleReturnBook();
        case "7" -> handleLoanHistory();
        case "0" -> {
          System.out.println("프로그램을 종료합니다.");
          return;
        }
        default -> System.out.println("잘못된 입력입니다.");
      }
    }
  }

  private void handleAddBook() {
    System.out.println("\n📌도서 추가");
    System.out.print("ID: ");
    String id = scanner.nextLine();
    System.out.print("제목: ");
    String title = scanner.nextLine();
    System.out.print("저자: ");
    String author = scanner.nextLine();
    System.out.print("ISBN: ");
    long isbn = Long.parseLong(scanner.nextLine());
    System.out.print("카테고리(,로 구분): ");
    List<String> categories = new ArrayList<>(List.of(scanner.nextLine().split(",")));
    System.out.print("가격: ");
    long price = Long.parseLong(scanner.nextLine());
    System.out.print("재고: ");
    int stock = Integer.parseInt(scanner.nextLine());

    Book book = new Book(id, title, author, isbn, categories, price, stock);
    try {
      bookService.addBook(book);
    } catch (Exception e) {
      System.out.println("오류 : " + e.getMessage());
    }
  }

  private void handleDeleteBook() {
    System.out.println("\n📌도서 삭제");
    System.out.print("ID: ");
    String id = scanner.nextLine();
    try {
      bookService.deleteBook(id);
    } catch (Exception e) {
      System.out.println("오류 : " + e.getMessage());
    }
  }

  private void handleSearchBook() {
    System.out.println("\n📌 도서 검색");
    System.out.print("검색어 입력 (제목/저자): ");
    String keyword = scanner.nextLine();
    List<Book> books = bookService.searchBooks(keyword);

    if (books.isEmpty()) {
      System.out.println("검색 결과가 없습니다.");
    } else {
      books.forEach(System.out::println);
    }
  }

  private void handleUpdateBook() {
    System.out.println("\n📌 도서 수정");
    System.out.print("수정할 책 ID: ");
    String id = scanner.nextLine();

    System.out.print("수정할 제목: ");
    String title = scanner.nextLine();
    System.out.print("수정할 저자: ");
    String author = scanner.nextLine();
    System.out.print("수정할 ISBN: ");
    long isbn = Long.parseLong(scanner.nextLine());
    System.out.print("수정할 카테고리(,로 구분): ");
    List<String> categories = new ArrayList<>(List.of(scanner.nextLine().split(",")));
    System.out.print("수정할 가격: ");
    long price = Long.parseLong(scanner.nextLine());
    System.out.print("수정할 재고: ");
    int stock = Integer.parseInt(scanner.nextLine());

    Book updatedBook = new Book(id, title, author, isbn, categories, price, stock);

    try {
      bookService.updateBook(id, updatedBook);
    } catch (Exception e) {
      System.out.println("오류 : " + e.getMessage());
    }
  }

  private void handleLoanBook() {
    System.out.println("\n📌 도서 대출");
    System.out.print("사용자 ID: ");
    String userId = scanner.nextLine();
    System.out.print("책 ID: ");
    String bookId = scanner.nextLine();

    try {
      userService.loanBook(userId, bookId);
    } catch (UserNotFoundException | BookNotFoundException | InsufficientStockException e) {
      System.out.println("오류 : " + e.getMessage());
    }
  }

  private void handleReturnBook() {
    System.out.println("\n📌 도서 반납");
    System.out.print("사용자 ID: ");
    String userId = scanner.nextLine();
    System.out.print("책 ID: ");
    String bookId = scanner.nextLine();

    try {
      userService.returnBook(userId, bookId);
    } catch (UserNotFoundException | BookNotFoundException e) {
      System.out.println("오류 : " + e.getMessage());
    }
  }

  private void handleLoanHistory() {
    System.out.println("\n📌 대출 기록 조회");
    System.out.print("사용자 ID: ");
    String userId = scanner.nextLine();
    try {
      userService.getUserLoanHistory(userId);
    } catch (UserNotFoundException e) {
      System.out.println("오류 : " + e.getMessage());
    }
  }
}
