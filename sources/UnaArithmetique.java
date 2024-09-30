/*
 * Base class that represent an unary arithmetique expression node inside the AST.
*/

public abstract class UnaArithmetique extends Unaire {
    /**
     * Constructor
     */
    public UnaArithmetique(String fl, int line, int col) {
        super(fl, line, col);
    }
}
