package Practice;

import java.util.Scanner;

public class BookManangement {
    public static Book [] books = new Book[100];
    public static int currentIndex = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("****************************MENU*************************");
            System.out.println("1. Danh sách sách");
            System.out.println("2. Thêm mới sách");
            System.out.println("3. Tính lợi nhuận của các sách");
            System.out.println("4. Cập nhật sách");
            System.out.println("5. Xóa sách");
            System.out.println("6. Sắp xếp sách theo lợi nhuận tăng dần");
            System.out.println("7. Tìm kiếm sách theo tác giả");
            System.out.println("8. Tìm kiếm sách theo khoảng giá");
            System.out.println("9. Thống kê sách theo mỗi tác giả");
            System.out.println("10. Thoát");
            System.out.print("lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayBooks();
                    break;
                case 2:
                    addBook(scanner);
                    break;
                case 3:
                    caculateInterest();
                    break;
                case 4:
                    updateBook(scanner);
                    break;
                case 5:
                    deleteBook(scanner);
                    break;
                case 6:
                    sortBooksByInterest();
                    break;
                case 7:
                    searchBookByAuthor(scanner);
                    break;
                case 8:
                    searchBookByRange(scanner);
                    break;
                case 9:
                    statisticByAuthor();
                    break;
                case 10:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ (1-10)");
            }
        }while (true);
    }

    public static void displayBooks(){
        for (int i = 0; i < currentIndex; i++) {
            books[i].displayData();
        }
    }

    public static void addBook(Scanner scanner) {
        System.out.println("Nhập số sách cần thêm");
        int bookNumber = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < bookNumber; i++) {
            Book book = new Book();
            book.inputData(scanner);
            books[currentIndex] = book;
            currentIndex++;
        }
    }

    public static void caculateInterest() {
        for (int i = 0; i < currentIndex; i++) {
            books[i].setInterest(books[i].calInterest());
        }
    }

    public static int findBookId(String bookId) {
        for (int i = 0; i < currentIndex; i++) {
            if (books[i].getBookId().equals(bookId)) {
                return i;
            }
        }
        return -1;
    }

    public static void updateBook(Scanner sc) {
        System.out.print("Nhập mã sách cần cập nhật: ");
        String bookId = sc.nextLine();
        int index = findBookId(bookId);
        if (index== -1) {
            System.err.printf("Không tìm thấy sách: %s", bookId);
        } else {
            boolean isExit = true;
            do {
                System.out.println("----- CẬP NHẬT THÔNG TIN SÁCH -----");
                System.out.println("1. Cập nhật tiêu đề sách");
                System.out.println("2. Cập nhật tác giả");
                System.out.println("3. Cập nhật giá nhập sách");
                System.out.println("4. Cập nhật giá bán sách");
                System.out.println("5. Cập nhật tên sách");
                System.out.println("6.Cập nhật năm của sách");
                System.out.println("7. Thoát");
                System.out.print("Chọn mục cần cập nhật: ");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Nhập tiêu đề mới: ");
                        books[index].setTitle(sc.nextLine());
                        break;
                    case 2:
                        System.out.print("Nhập tên tác giả mới: ");
                        books[index].setAuthor(sc.nextLine());
                        break;
                    case 3:
                        System.out.print("Nhập giá nhập mới: ");
                        books[index].setImportPrice(Double.parseDouble(sc.nextLine()));
                        break;
                    case 4:
                        System.out.print("Nhập giá bán sách mới: ");
                        books[index].setExportPrice(Double.parseDouble(sc.nextLine()));
                        break;
                    case 5:
                        System.out.print("Nhập tên sách mới: ");
                        books[index].setBookName(sc.nextLine());
                        break;
                    case 6:
                        System.out.println("Nhap nam xuat ban moi");
                        books[index].setYear(Integer.parseInt((sc.nextLine())));
                        break;
                    case 7:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-7!");
                }
            } while (isExit);
        }
    }

    public static void deleteBook(Scanner sc) {
        System.out.println("Nhập ID sách cần xóa: ");
        String bookId = sc.nextLine();
        int indexDelete = findBookId(bookId);
        if (indexDelete == -1) {
            System.out.println("Không tìm thấy sách!");
        } else {
            for (int i = indexDelete; i < currentIndex - 1; i++) {
                books[i] = books[i + 1];
            }
            currentIndex--;
        }
    }

    public static void sortBooksByInterest() {
        for (int i = 0; i < currentIndex - 1; i++) {
            for (int j = i + 1; j < currentIndex; j++) {
                if (books[j].getInterest() < books[i].getInterest()) {
                    Book temp = books[i];
                    books[i] = books[j];
                    books[j] = temp;
                }
            }
        }
    }

    public static void searchBookByAuthor(Scanner sc) {
        System.out.print("Nhập tên tác giả: ");
        String author = sc.nextLine();
        for (int i = 0; i < currentIndex; i++) {
            if (books[i].getAuthor().equals(author)) {
                System.out.println(books[i].getBookName());
            }
        }
    }

    public static void searchBookByRange(Scanner sc) {
        System.out.print("Nhập giá thấp nhất: ");
        double minPrice = Double.parseDouble(sc.nextLine());
        System.out.print("Nhập giá cao nhất: ");
        double maxPrice = Double.parseDouble(sc.nextLine());
        for (int i = 0; i < currentIndex; i++) {
            if (books[i].getExportPrice() >= minPrice && books[i].getExportPrice() <= maxPrice) {
                books[i].displayData();
            }
        }
    }

    public static void statisticByAuthor() {
        String[] authorBooks = new String[100];
        int indexAuthor = 0;
        for (int i = 0; i < currentIndex; i++) {
            boolean exists = false;
            for (int j = 0; j < indexAuthor; j++) {
                if (books[i].getAuthor().equals(authorBooks[j])) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                authorBooks[indexAuthor] = books[i].getAuthor();
                indexAuthor++;
            }
        }

        for (int i = 0; i < indexAuthor; i++) {
            int count = 0;
            for (int j = 0; j < currentIndex; j++) {
                if (authorBooks[i].equals(books[j].getAuthor())) { 
                    count++;
                }
            }
            System.out.println("Tác giả " + authorBooks[i] + " có " + count + " sách.");
        }
    }
}
