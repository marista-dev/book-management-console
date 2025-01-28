package bookmanagement.exception;

public class BooksAlreadyExistException extends RuntimeException {

  public BooksAlreadyExistException(String message) {
    super(message);
  }
}
