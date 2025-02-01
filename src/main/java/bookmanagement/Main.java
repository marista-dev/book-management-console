package bookmanagement;

import bookmanagement.domain.Book;
import bookmanagement.domain.User;
import bookmanagement.service.BookService;
import bookmanagement.service.UserService;
import bookmanagement.ui.LibraryController;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    // 초기 사용자 및 도서 목록 생성
    List<User> users = new ArrayList<>();
    List<Book> books = new ArrayList<>();

    // 예제 데이터 추가
    users.add(new User("1", "홍길동", new ArrayList<>()));
    users.add(new User("2", "이순신", new ArrayList<>()));

    BookService bookService = new BookService(books);
    UserService userService = new UserService(users, books);
    LibraryController controller = new LibraryController(bookService, userService);

    controller.run();
  }
}
