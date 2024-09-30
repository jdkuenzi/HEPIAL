/*
 * Represent an equal comparaison expression node inside the AST.
*/

public class Egal extends Relation {
    /**
     * Constructor
     */
    public Egal(String fl, int line, int col) {
        super(fl, line, col);
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}
