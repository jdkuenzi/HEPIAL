/*
 * Represent an addition expression node inside the AST.
*/

public class Divide extends Arithmetique {
    /**
     * Constructor
     */
    public Divide(String fl, int line, int col) {
        super(fl, line, col);
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}