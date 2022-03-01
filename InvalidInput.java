/**
 * Dennis Lam
 * CSE 017 Spring
 * Feb 26th 2022
 * Project 1
 * Last edited: Feb 26th 2022
 */
public class InvalidInput extends Exception {
    /**
     * Empty Constructor
     */
    public InvalidInput() {
    }

    /**
     * 1 paramteter constructor
     * 
     * @param errorMessage
     */
    public InvalidInput(String errorMessage) {
        super(errorMessage);
    }

}
