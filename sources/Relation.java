/*
 * Base class that represent a binary relational expression node inside the AST.
 */

public abstract class Relation extends Binaire {
    /**
     * Constructor
     */
    public Relation(String fl, int line, int col) {
        super(fl, line, col);
    }
}
