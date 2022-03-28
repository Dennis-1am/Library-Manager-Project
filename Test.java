/**
 * Dennis Lam
 * CSE 017 Spring
 * Feb 26th 2022
 * Project 1
 * Last edited: Feb 26th 2022
 */
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Test {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        File file = new File("titles.txt");

        LibraryMedia list[] = new LibraryMedia[21];

        LibraryManager manager = new LibraryManager(); // object created for the manipulation of the catalog

        try (Scanner fileReader = new Scanner(file)) {
            String media = "";
            String media1 = "";
            String tempMedia[] = new String[6];
            int index = 0;
            while (fileReader.hasNextLine()) {
                media1 = fileReader.nextLine();
                if (media1.matches("P-[0-9]{3}-[0-9]{3}-[0-9]{3}")) {

                    for (int i = 0; i < tempMedia.length; i++) {
                        media = fileReader.nextLine();
                        tempMedia[i] = media;
                    }
                    
                    String callNumber = media1;
                    String title = tempMedia[0];
                    String publisher = tempMedia[1];
                    int year = Integer.valueOf(tempMedia[2]);
                    int numCopies = Integer.valueOf(tempMedia[3]);
                    int month = Integer.valueOf(tempMedia[4]);
                    int issueNumber = Integer.valueOf(tempMedia[5]);

                    LibraryMedia Periodical = new Periodical(title, publisher, year, callNumber, numCopies, month,
                            issueNumber);
                    list[index] = Periodical;
                    index++;

                } else if (media1.matches("B-[0-9]{3}-[0-9]{3}-[0-9]{3}")) {
                    for (int i = 0; i < tempMedia.length; i++) {
                        media = fileReader.nextLine();
                        tempMedia[i] = media;
                    }

                    String callNumber = media1;
                    String title = tempMedia[0];
                    String publisher = tempMedia[1];
                    int year = Integer.valueOf(tempMedia[2]);
                    int numCopies = Integer.valueOf(tempMedia[3]);
                    String author = tempMedia[4];
                    String ISBN = tempMedia[5];

                    LibraryMedia Book = new Book(title, publisher, year, callNumber, numCopies, author, ISBN);
                    list[index] = Book;
                    index++;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        Scanner scnr = new Scanner(System.in);
        boolean flag = true;
        int userOption = 0;
        int y = 0;
        while (flag) {

            System.out.println();
            System.out.println(
                    "Select an operation:\n1: View all titles\n2: Search by call number\n3: Search by title\n4: Search by year\n5: Add new title\n6: Remove title\n7: Sort titles by year\n8: View Titles due for restoration\n9: Exit");
            if (scnr.hasNextInt()) {
                userOption = scnr.nextInt();
                if (userOption >= 1 && userOption <= 9) {
                    switch (userOption) {
                        case 1: {
                            manager.viewTitle(list);
                            break;
                        }
                        case 2: {
                            manager.searchCallNumber(list);
                            break;
                        }
                        case 3: {
                            manager.searchByTitle(list);
                            break;
                        }
                        case 4: {
                            manager.searchByYear(list);
                            break;
                        }
                        case 5: {
                            try {
                                LibraryMedia l = manager.addTitle();
                                y++;
                                LibraryMedia[] tempList = Arrays.copyOf(list, list.length);
                                ;
                                list = new LibraryMedia[21 + y];
                                for (int i = 0; i < tempList.length; i++) {
                                    list[i] = tempList[i];
                                }
                                list[list.length - 1] = l;
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            break;
                        }
                        case 6: {
                            Scanner usrInput = new Scanner(System.in);
                            System.out.println("Please Enter a callNumber");
                            String callNumber = usrInput.nextLine();
                            LibraryMedia[] tempList = Arrays.copyOf(list, list.length);
                            int numOfLibraryMedia = tempList.length - 1;
                            numOfLibraryMedia = manager.removeTitle(tempList, callNumber, numOfLibraryMedia); //
                            list = new LibraryMedia[numOfLibraryMedia];
                            for (int i = 0; i < numOfLibraryMedia; i++) {
                                list[i] = tempList[i];
                            }
                            usrInput.close();
                            break;
                        }
                        case 7: {
                            manager.sortByYear(list);
                            break;
                        }
                        case 8: {
                            manager.viewRestoration(list);
                            break;
                        }
                        case 9: {
                            flag = false;
                            break;
                        }
                    }
                } else {
                    System.out.println("Invalid input, please enter an integer from 1-9");
                    continue;
                }
            } else {
                System.out.println("Invalid input, please enter an integer from 1-9");
                scnr.nextLine();
            }
        }
        scnr.close();
        try (FileWriter writer = new FileWriter("titles.txt")) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].getCallNumber().matches("P-[0-9]{3}-[0-9]{3}-[0-9]{3}")) {
                    writer.write(list[i].getCallNumber() + "\n");
                    writer.write(list[i].getTitle() + "\n");
                    writer.write(list[i].getPublisher() + "\n");
                    writer.write(list[i].getYear() + "\n");
                    writer.write(list[i].getCopies() + "\n");
                    writer.write(((Periodical) list[i]).getMonth() + "\n");
                    writer.write(((Periodical) list[i]).getIssueNumber()+ "\n");
                    writer.flush();
                } else if (list[i].getCallNumber().matches("B-[0-9]{3}-[0-9]{3}-[0-9]{3}")) {
                    writer.write(list[i].getCallNumber() + "\n");
                    writer.write(list[i].getTitle() + "\n");
                    writer.write(list[i].getPublisher() + "\n");
                    writer.write(list[i].getYear() + "\n");
                    writer.write(list[i].getCopies() + "\n");
                    writer.write(((Book) list[i]).getAuthor() + "\n");
                    writer.write(((Book) list[i]).getISBN() + "\n");
                    writer.flush();
                }
            }
        } catch (Exception e) {

        }
        System.out.println("Thank You for using this managment program");
    }
}