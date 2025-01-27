package bookmanagement.domain;

import java.util.ArrayList;
import java.util.List;

public class User {

  private final String id;
  private String name;
  private List<Book> loanedBooks;

  public User(String id, String name, List<Book> loanedBooks) {
    this.id = id;
    this.name = name;
    this.loanedBooks = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<Book> getLoanedBooks() {
    return loanedBooks;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "User{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", loanedBooks=" + loanedBooks +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return id.equals(user.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
