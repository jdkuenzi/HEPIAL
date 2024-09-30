/*
 * Represent a number node inside the AST.
*/

public class True extends Expression {

    public True(String fl, int line, int col) {
        super(fl, line, col);
    }

    public boolean getValeur() {
        return true;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}
