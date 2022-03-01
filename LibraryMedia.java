/**
 * Dennis Lam
 * CSE 017 Spring
 * Feb 26th 2022
 * Project 1
 * Last edited: Feb 26th 2022
 */
abstract class LibraryMedia implements Comparable<LibraryMedia>, Restorable {
     private String title;
     private String publisher;
     private int year;
     private String callNumber;
     private int copies;

     /**
      * empty constructor
      */
     LibraryMedia() {
          this.title = "";
          this.publisher = "";
          this.year = 0;
          this.callNumber = "";
          this.copies = 0;
     }

     /**
      * 5 parameter constructor
      * 
      * @param title
      * @param publisher
      * @param year
      * @param callNumber
      * @param copies
      */
     LibraryMedia(String title, String publisher, int year, String callNumber, int copies) {
          this.title = title;
          this.publisher = publisher;
          this.year = year;
          this.callNumber = callNumber;
          this.copies = copies;
     }

     /**
      * @return String
      */
     public String getTitle() {
          return title;
     }

     /**
      * @param title
      */
     public void setTitle(String title) {
          this.title = title;
     }

     /**
      * @return String
      */
     public String getPublisher() {
          return publisher;
     }

     /**
      * @param publisher
      */
     public void setPublisher(String publisher) {
          this.publisher = publisher;
     }

     /**
      * @return int
      */
     public int getYear() {
          return year;
     }

     /**
      * @param year
      */
     public void setYear(int year) {
          this.year = year;
     }

     /**
      * @return String
      */
     public String getCallNumber() {
          return callNumber;
     }

     /**
      * @param callNumber
      */
     public void setCallNumber(String callNumber) {
          this.callNumber = callNumber;
     }

     /**
      * @return int
      */
     public int getCopies() {
          return copies;
     }

     /**
      * @param copies
      */
     public void setCopies(int copies) {
          this.copies = copies;
     }

     /**
      * @return String
      */
     @Override
     public String toString() {
          return String.format("%-25s %-50s %-30s %-15s", callNumber, title, publisher, year); // String formatter with
                                                                                               // 'x' spacing per string
     }

     /**
      * @param libraryMedia
      * @return int
      */
     @Override
     public int compareTo(LibraryMedia libraryMedia) {
          int i = 0;
          if (this.year == libraryMedia.getYear()) {
               i = 0;
          } else if (this.year < libraryMedia.getYear()) {
               i = 1;
          } else if (this.year > libraryMedia.getYear()) {
               i = -1;
          }
          return i;
     }

     /**
      * @param libraryMedia
      * @return boolean
      */
     @Override
     public boolean isRestorable(LibraryMedia libraryMedia) {
          boolean restorable = false;
          if (2022 - libraryMedia.getYear() >= 20) {
               restorable = true;
          } else {
               restorable = false;
          }
          return restorable;
     }
}