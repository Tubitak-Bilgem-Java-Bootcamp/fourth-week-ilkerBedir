package model;

import java.time.LocalDate;

//Book sınıfı kitap ismi,sayfa sayısı,yazar ismi,yayın tarihi
public class Book {
  private String name;
  private int pageCount;
  private String authorName;
  private LocalDate publishDate;

  public Book(String name, int pageCount, String authorName, LocalDate publishDate) {
    this.name = name;
    this.pageCount = pageCount;
    this.authorName = authorName;
    this.publishDate = publishDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public LocalDate getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(LocalDate publishDate) {
    this.publishDate = publishDate;
  }

  @Override
  public String toString() {
    return "Kitap{" +
      "Kitap İsmi = '" + name + '\'' +
      ", Sayfa Sayısı = " + pageCount +
      ", Yazar İsmi = '" + authorName + '\'' +
      ", Yayınlanma Tarihi = " + publishDate +
      '}';
  }
}
