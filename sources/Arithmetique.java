/*
 * Base class that represent a binary arithmetique expression node inside the AST.
*/

public abstract class Arithmetique extends Binaire {
    /**
     * Constructor
     */
    public Arithmetique(String fl, int line, int col) {
        super(fl, line, col);
    }
}
