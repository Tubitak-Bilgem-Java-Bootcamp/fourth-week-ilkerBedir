import model.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    //Kitap Ekleme
    ArrayList<Book> books = new ArrayList<>(10);
    books.add(new Book("Simyacı", 184, "Paulo Coelho", LocalDate.of(2019, 9, 28)));
    books.add(new Book("Kırmızı Pazartesi", 100, "Gabriel Garcia Marquez", LocalDate.of(2019, 10, 30)));
    books.add(new Book("Hayvan Çiftliği", 152, "George Orwell", LocalDate.of(2000, 9, 28)));
    books.add(new Book("Cesur Yeni Dünya", 272, "Aldous Huxley", LocalDate.of(2019, 9, 2)));
    books.add(new Book("Uğultulu Tepeler", 408, "Emily Bronte", LocalDate.of(2019, 8, 1)));
    books.add(new Book("Cimri", 199, "Adam Coelho", LocalDate.of(2019, 9, 28)));
    books.add(new Book("Sihirbazın Kitabı", 80, "Gökhan Yılmaz", LocalDate.of(2019, 10, 30)));
    books.add(new Book("Yabancı", 253, "Öznur Yıldırım", LocalDate.of(2000, 9, 28)));
    books.add(new Book("Gençlik", 272, "Fatma Huxley", LocalDate.of(2019, 9, 2)));
    books.add(new Book("Güneşli Tepeler", 852, "Cenk Bronte", LocalDate.of(2019, 8, 1)));

    System.out.println("-------Kitap ve Yazar İsimleri--------");
    Map<String, String> bookMap = books.stream().collect(Collectors.toMap(book -> book.getName(), book -> book.getAuthorName()));
    bookMap.forEach((book, author) -> System.out.println("Kitap İsmi : " + book + ";\tYazar İsmi : " + author));

    System.out.println("\n---------Sayfa Sayısı 100den Fazla Olan Kitaplar-----------");
    List<Book> bookList = books.stream().filter(book -> book.getPageCount() > 100).collect(Collectors.toList());
    bookList.stream().forEach(book -> System.out.println(book.toString()));
  }
}
