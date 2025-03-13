package Practice;

import java.util.Scanner;

public class Book {
    private String bookId;
    private String bookName;
    private Double importPrice;
    private Double exportPrice;
    private String title;
    private String author;
    private Double interest;
    private Integer year;
    public Book() {}

    public Book(String bookId, String bookName, Double importPrice, Double exportPrice, String title, String author, Double interest, Integer year) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.title = title;
        this.author = author;
        this.interest = interest;
        this.year = year;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(Double importPrice) {
        this.importPrice = importPrice;
    }

    public Double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(Double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void inputData(Scanner scanner) {
        do {
            System.out.println("Nhập mã sách(Bxxxx)");
            this.bookId = scanner.nextLine();
        }while (!bookId.matches("B\\w{4}"));

        do {
            System.out.println("Nhập tên sách (6-100)");
            this.bookName = scanner.nextLine();
        }while (bookName.length() < 6 || bookName.length() > 100);

        do {
            System.out.println("Nhập Giá nhập (>0)");
            this.importPrice = Double.parseDouble(scanner.nextLine());
        }while (importPrice <= 0);

        do {
            System.out.println("Nhập giá bán (> giá nhập 10% trở lên)");
            this.exportPrice = Double.parseDouble(scanner.nextLine());
        }while (exportPrice <= importPrice*1.1);

        do {
            System.out.println("Nhập tiêu đề sách");
            this.title = scanner.nextLine();
        }while (title.trim().isEmpty());

        do {
            System.out.println("Nhập tác giả");
            this.author = scanner.nextLine();
        }while (author.trim().isEmpty());

        do {
            System.out.println("Nhập năm xuất bản");
            this.year = Integer.parseInt(scanner.nextLine());
        }while (year < 1970);
    }

    public void displayData() {
        System.out.printf("Mã sách: %s \t Tên sách: %s \t Giá nhập: %f \t Giá bán: %f \n", bookId , bookName, importPrice, exportPrice);
        System.out.printf("Tiêu đề: %s \t Tác giả: %s \t Lợi nhuận: %f\t Năm xuất bản: %d \n", title, author, interest, year);
        System.out.println("---------------------");
    }

    public Double calInterest() {
        return this.interest = exportPrice - importPrice;
    }
}
