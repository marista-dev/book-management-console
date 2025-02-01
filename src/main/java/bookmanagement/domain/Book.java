package bookmanagement.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Book {

  private final String id;
  private String title;
  private String author;
  private long isbn;
  private List<String> categories;
  private long price;
  private int stock;

  public Book(String id, String title, String author, long isbn, List<String> categories, long price, int stock) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.categories = new ArrayList<>(categories);
    this.price = price;
    this.stock = stock;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public Long getIsbn() {
    return isbn;
  }

  public List<String> getCategories() {
    return Collections.unmodifiableList(categories);
  }

  public Long getPrice() {
    return price;
  }

  public int getStock() {
    return stock;
  }


  public void setTitle(String title) {
    this.title = title;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setIsbn(Long isbn) {
    this.isbn = isbn;
  }

  public void setCategories(List<String> categories) {
    this.categories = new ArrayList<>(categories);
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  @Override
  public String toString() {
    return "Book{" +
            "id='" + id + '\'' +
            ", title='" + title + '\'' +
            ", author='" + author + '\'' +
            ", isbn=" + isbn +
            ", categories=" + categories +
            ", price=" + price +
            ", stock=" + stock +
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
    Book book = (Book) o;
    return Objects.equals(id, book.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
