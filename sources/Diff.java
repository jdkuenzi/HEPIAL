/*
 * Represent an addition expression node inside the AST.
*/

public class Diff extends Relation {
    /**
     * Constructor
     */
    public Diff(String fl, int line, int col) {
        super(fl, line, col);
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}
