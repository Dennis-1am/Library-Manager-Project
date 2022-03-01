public class Periodical extends LibraryMedia {
    private int month;
    private int issueNumber;

    /**
     * empty constructor
     */
    public Periodical() {
        super();
        this.month = 0;
        this.issueNumber = 0;
    }

    /**
     * 7 parameter constructor
     * 
     * @param title
     * @param publisher
     * @param year
     * @param callNumber
     * @param copies
     * @param month
     * @param issueNumber
     */
    public Periodical(String title, String publisher, int year, String callNumber, int copies, int month,
            int issueNumber) {
        super(title, publisher, year, callNumber, copies);
        this.month = month;
        this.issueNumber = issueNumber;
    }

    /**
     * @return int
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return int
     */
    public int getIssueNumber() {
        return issueNumber;
    }

    /**
     * @param issueNumber
     */
    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
