import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryManager {
    Scanner manageScanner = new Scanner(System.in);

    /**
     * @param list
     */
    public void viewTitle(LibraryMedia[] list) {
        System.out.println();
        System.out.println(String.format("%-25s %-50s %-30s %-15s", "CallNumber", "Title", "Publisher", "Year"));
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
    }

    /**
     * @param list
     */
    public void searchCallNumber(LibraryMedia[] list) {
        boolean flag = true;
        boolean inputFlag = true;
        System.out.println("Please enter a call number:");
        String usrCN = manageScanner.nextLine();

        while (inputFlag) {
            if (!usrCN.matches("P-[0-9]{3}-[0-9]{3}-[0-9]{3}|B-[0-9]{3}-[0-9]{3}-[0-9]{3}")) {
                System.out.println("Invalid Call Number. Must be B-ddd-ddd-ddd or P-ddd-ddd-ddd");
                usrCN = manageScanner.nextLine();
            } else {
                inputFlag = false;
            }
        }

        for (int i = 0; i < list.length; i++) {
            if (list[i].getCallNumber().equals(usrCN) && usrCN.matches("P-[0-9]{3}-[0-9]{3}-[0-9]{3}")) {
                System.out.println();
                System.out.println(
                        "Title found:\nCallNumber: " + list[i].getCallNumber() + "\nTitle: " + list[i].getTitle()
                                + "\nPublisher: " + list[i].getPublisher() + "\nYear: " + list[i].getYear()
                                + "\nCopies: " + list[i].getCopies() + "\nMonth: "
                                + ((Periodical) list[i]).getMonth()
                                + "\nIssue Number: " + ((Periodical) list[i]).getIssueNumber());
                flag = false;
                break;
            } else if (list[i].getCallNumber().equals(usrCN) && usrCN.matches("B-[0-9]{3}-[0-9]{3}-[0-9]{3}")) {
                System.out.println();
                System.out.println(
                        "\nTitle found:\nCallNumber: " + list[i].getCallNumber() + "\nTitle: " + list[i].getTitle()
                                + "\nPublisher: " + list[i].getPublisher() + "\nYear: " + list[i].getYear()
                                + "\nCopies: " + list[i].getCopies() + "\nAuthor: " + ((Book) list[i]).getAuthor()
                                + "\nISBN: " + ((Book) list[i]).getISBN());
                flag = false; // stays false so that we don't get title is not found
                break;
            }
        }
        if (flag) {
            System.out.println("No titles found");
        }
    }

    /**
     * @param list
     */
    public void searchByTitle(LibraryMedia[] list) {
        System.out.println("Please enter a title:");
        String title = manageScanner.nextLine();

        boolean flag = true;
        int counter = 0;
        LibraryMedia[] tempFound = new LibraryMedia[10];
        for (int i = 0; i < list.length; i++) {
            if (list[i].getTitle().equals(title)) {
                for (int j = counter; j < counter + 1; j++) {
                    tempFound[j] = list[i];
                }
                counter++;
                flag = false;
            }
        }
        if (flag) {
            System.out.println("No titles found");
        }
        if (!flag) {
            System.out.println();
            System.out.println("List of titles found: " + counter);
            System.out.println(String.format("%-25s %-50s %-30s %-15s", "CallNumber", "Title", "Publisher", "Year"));
            for (int j = 0; j < counter; j++) {
                System.out.println(tempFound[j]);
            }
        }
    }

    /**
     * @param list
     */
    public void searchByYear(LibraryMedia[] list) {
        System.out.println("Please enter a year:");
        int year = manageScanner.nextInt();
        boolean inputFlag = true;

        while (inputFlag) {
            if (!(year >= 1900 && year <= 2022)) {
                System.out.println("Invalid Year. Must be between 1900 and 2022");
                year = manageScanner.nextInt();
            } else {
                inputFlag = false;
            }
        }

        boolean flag = true;
        int counter = 0;
        LibraryMedia[] tempFound = new LibraryMedia[10];
        for (int i = 0; i < list.length; i++) {
            if (list[i].getYear() == year) {
                for (int j = counter; j < counter + 1; j++) {
                    tempFound[j] = list[i];
                }
                counter++;
                flag = false;
            }
        }
        if (flag) {
            System.out.println("No titles found");
        }

        if (!flag) {
            System.out.println();
            System.out.println("List of titles found: " + counter);
            System.out.println(String.format("%-25s %-50s %-30s %-15s", "CallNumber", "Title", "Publisher", "Year"));
            for (int j = 0; j < counter; j++) {
                System.out.println(tempFound[j]);
            }
        }
    }

    /**
     * @return LibraryMedia
     * @throws InvalidInput
     */
    public LibraryMedia addTitle() throws InvalidInput {
        String title = "", publisher = "", typeMedia = "", callNumber = "", author = "", ISBN = "";
        int year = 0, numCopies = 0, month = 0, issueNumber = 0;

        boolean flag = true;
        Scanner usrInput = new Scanner(System.in);

        try {
            boolean flag1 = true, flag2 = true, flag3 = true, flag4 = true, flag5 = true, flag6 = true, flag7 = true;
            while (flag) {
                if (flag1) {
                    System.out.println("Enter the Title:");
                    title = usrInput.nextLine();
                    flag1 = false;
                }

                if (flag2) {
                    System.out.println("Enter the Publisher:");
                    publisher = usrInput.nextLine();
                    flag2 = false;
                }

                if (flag3) {
                    System.out.println("Enter the Year:");
                    String tempYear = usrInput.nextLine();
                    if (String.valueOf(tempYear).matches("19[0-9][0-9]|200[0-9]|201[1-9]|202[0-2]")) {
                        year = Integer.valueOf(tempYear);
                        flag3 = false;
                    } else {
                        continue;
                    }
                }

                if (flag4) {
                    System.out.println("Enter the number of copies:");
                    numCopies = usrInput.nextInt();
                    usrInput.nextLine();
                    flag4 = false;
                }

                if (flag5) {
                    System.out.println("Enter a Media Type:");
                    String tempMedia = usrInput.nextLine();
                    if (tempMedia.equals("Book") || tempMedia.equals("Periodical")) {
                        typeMedia = tempMedia;
                        flag5 = false;
                    } else {
                        continue;
                    }
                }

                if (flag6) {
                    System.out.println("Enter a Call Number:");
                    String callTempNum = usrInput.nextLine();
                    if (typeMedia.equals("Book") && callTempNum.matches("B-[0-9]{3}-[0-9]{3}-[0-9]{3}")) {
                        callNumber = callTempNum;
                        flag6 = false;
                    } else if (typeMedia.equals("Periodical") && callTempNum.matches("P-[0-9]{3}-[0-9]{3}-[0-9]{3}")) {
                        callNumber = callTempNum;
                        flag6 = false;
                    } else {
                        continue;
                    }
                }

                if (flag7) {
                    if (typeMedia.equals("Book")) {
                        System.out.println("Enter the author");
                        author = usrInput.nextLine();
                        System.out.println("Enter the ISBN (10 digits");
                        String tempISBN = usrInput.nextLine();
                        if (tempISBN.matches("[0-9]{10}")) {
                            ISBN = tempISBN;
                            System.out.println("Title added successfully");
                            flag7 = false;
                            break;
                        } else {
                            continue;
                        }
                    }
                    if (typeMedia.equals("Periodical")) {
                        System.out.println("Enter the month");
                        int tempMonth = usrInput.nextInt();
                        if (tempMonth > 0 && tempMonth <= 12) {
                            month = tempMonth;
                            flag7 = false;
                        } else {
                            continue;
                        }
                        System.out.println("Enter the issue number");
                        if (usrInput.hasNextInt()) {
                            issueNumber = usrInput.nextInt();
                            break;
                        } else {
                            throw new InvalidInput("Invalid Input");
                        }
                    }
                }
            }
        } catch (InputMismatchException e) {
            throw new InvalidInput("Invalid Input");
        }

        LibraryMedia[] genList = new LibraryMedia[1]; // create object array of type LibraryMedia with length 1

        if (typeMedia.equals("Book")) {
            genList[0] = new Book(title, publisher, year, callNumber, numCopies, author, ISBN);
        } else if (typeMedia.equals("Periodical")) {
            genList[0] = new Periodical(title, publisher, year, callNumber, numCopies, month, issueNumber);
        }
        return genList[0];
    }

    /**
     * @param list[]
     * @param callNumber
     * @param i++
     * @return int
     */
    public int removeTitle(LibraryMedia list[], String callNumber, int numOfLibraryMedia) { // numOfLibraryMedia is a
                                                                                            // placeholder for the
                                                                                            // length of the object
                                                                                            // array
        boolean flag = false;
        int removeIndex = 0;
        for (int i = 0; i < list.length; i++) {
            if (callNumber.equals(list[i].getCallNumber())) { // checked
                removeIndex = i;
                flag = true;
            }
        }
        if (flag = true) {
            System.out.println("Removing Media");
            while (numOfLibraryMedia > removeIndex) {
                LibraryMedia temp = list[removeIndex + 1];
                list[removeIndex + 1] = list[removeIndex];
                list[removeIndex] = temp;
                removeIndex++;
            }
            list[removeIndex] = null;
            numOfLibraryMedia -= 1;
        }
        return numOfLibraryMedia; // return the new length of the array
    }

    /**
     * @param list[]
     */
    public void sortByYear(LibraryMedia list[]) {
        for (int i = 0; i < list.length; i++) {
            LibraryMedia currMedia = list[i];
            int j = i;
            while (j > 0
                    && Integer.valueOf(currMedia.getYear()).compareTo(Integer.valueOf(list[j - 1].getYear())) < 0) {
                list[j] = list[j - 1];
                j--;
            }
            list[j] = currMedia;
        }
    }

    /**
     * @param list[]
     */
    public void viewRestoration(LibraryMedia list[]) {
        LibraryMedia[] genList = new LibraryMedia[1000];
        int counter = 0; // counter for keeping track of how many restorables and to print out the new
                         // object array
        int tempYear = 0;
        for (int i = 0; i < list.length; i++) {
            tempYear = list[i].getYear();
            if ((2022 - tempYear) > 20) {
                genList[counter] = list[i];
                counter++;
            }
        }
        System.out.println("List of titles found: " + counter);
        for (int j = 0; j < counter; j++) {
            System.out.println(genList[j]);
        }
    }
}
