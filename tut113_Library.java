import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

class Book {
    String name;
    String author;

    public Book(String name,String author) {
        this.name = name;
        this.author = author;
    }
}

class BookIssuedException extends Exception{
    @Override
    public String toString() {
        return super.toString()+"Book is already issued.Try another!!!";
    }
}

class BookNotIssuedException extends Exception{
    @Override
    public String toString() {
        return super.toString()+"Book is not currently issued.Try another!!!";
    }
}




public class tut113_Library {

    public static void issueBook(String name,String book) throws BookIssuedException{
        File myFile = new File("tut113_IssuedBook.txt");
        try {
            Scanner ss = new Scanner(myFile);
        while(ss.hasNextLine()){
            if (book.equals(ss.nextLine())){
                throw new BookIssuedException();
            }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: "+e);
        }

        try {
            FileWriter frr = new FileWriter("tut113_IssuedBook.txt",true);
            frr.write(book);
            FileWriter frr2 = new FileWriter("tut113_IssuedBookAllDetails.txt",true);
            LocalDateTime dt = LocalDateTime.now();
            frr2.write(book +" -- issued by "+name+"["+dt+"]\n");
            frr.close();
            frr2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The book is issued successfully.Collect from the counter!");
    }

    public static void returnBook(String name,String book) throws BookNotIssuedException {
        int i = 0;
        Scanner scCIIB = new Scanner("tut113_IssuedBook.txt");
        while (scCIIB.hasNextLine()){
            if (book.equals(scCIIB.nextLine())){
                i = 1;
                System.out.println("kakkkakka");
            }
        scCIIB.close();
        }
        if(i == 0){
            throw new BookNotIssuedException();
        }
        try {
            FileWriter frr2 = new FileWriter("tut113_IssuedBookAllDetails.txt",true);
            LocalDateTime dt = LocalDateTime.now();
            frr2.write(book +" -- returned by "+name+"["+dt+"]\n");
            frr2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The book is returned successfully.");
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("1: Add Book\n2: Issue Book\n3: Return Issued Book\n4: See Issued Books All Details\n5: See Current Issued Books\n6: See All Books");
        int a = sc.nextInt();


        if (a == 1){
            Scanner scc = new Scanner(System.in);
            System.out.println("Enter the Book Name:");
            String s = scc.nextLine();
            System.out.println("Enter the Author Name:");
            String s2 = scc.nextLine();
            Book b1 = new Book(s,s2);
            try {
                FileWriter fr = new FileWriter("tut113_AvailableBooks.txt",true);
                fr.write(b1.name+" -- by "+b1.author+"\n");
                fr.close();
            } catch (IOException e) {
                System.out.println("ERROR: "+e);
            }
            System.out.println("Book is successfully added.");
        }

        else if (a == 2) {
            Scanner scc = new Scanner(System.in);
            System.out.println("Enter the book name:");
            String book = scc.nextLine();
            System.out.println("Enter your name:");
            String name = scc.nextLine();
            try {
                issueBook(name,book);
            } catch (BookIssuedException | RuntimeException e) {
                System.out.println("ERROR: "+e);
            }
        }

        else if (a == 3) {
            Scanner scc1 = new Scanner(System.in);
            System.out.println("Enter the book name:");
            String book = scc1.nextLine();
            System.out.println("Enter your name:");
            String name = scc1.nextLine();
            try {
                returnBook(name,book);
            } catch (BookNotIssuedException | RuntimeException e) {
                System.out.println("ERROR: "+e);
            }
        }

        else if (a == 4) {
            System.out.println("All issued books related details-");
            File myFile = new File("tut113_IssuedBookAllDetails.txt");
            try {
                Scanner  scIB = new Scanner(myFile);
                while (scIB.hasNextLine()){
                    System.out.println(scIB.nextLine());
                }
                scIB.close();
            } catch (FileNotFoundException e) {
                System.out.println("ERROR: No Details Is Available\n"+e);
            }

        }

        else if (a == 5) {
            System.out.println("Currently issued books-");
            File myFile = new File("tut113_IssuedBook.txt");
            try {
                Scanner scCIB = new Scanner(myFile);
                while (scCIB.hasNextLine()){
                    System.out.println(scCIB.nextLine());
                }scCIB.close();
            } catch (FileNotFoundException e) {
                System.out.println("ERROR: No Book Is Issued\n"+e);
            }
        }

        else if (a == 6) {
            System.out.println("All books of the library-");
            File myFile = new File("tut113_AvailableBooks.txt");
            try {
                Scanner scAB = new Scanner(myFile);
                if (!scAB.hasNextLine()){
                    System.out.println("ERROR: No Book Is Available");
                }
                while (scAB.hasNextLine()){
                    System.out.println(scAB.nextLine());
                }
                scAB.close();
            } catch (FileNotFoundException e) {
                System.out.println("ERROR: No Book Is Available\n"+e);
            }

        }else {
            System.out.println("Error key entered! Try again!");
        }

    }
}
