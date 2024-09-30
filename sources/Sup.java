/*
 * Represent an addition expression node inside the AST.
*/

public class Sup extends Relation {
    /**
     * Constructor
     */
    public Sup(String fl, int line, int col) {
        super(fl, line, col);
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}
