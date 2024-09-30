/*
 * Represent a number node inside the AST.
*/

public class False extends Expression {

    public False(String fl, int line, int col) {
        super(fl, line, col);
    }

    public boolean getValeur() {
        return false;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}