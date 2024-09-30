public class Not extends UnaArithmetique {
    /**
     * Constructor
     */
    public Not(String fl, int line, int col) {
        super(fl, line, col);
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}