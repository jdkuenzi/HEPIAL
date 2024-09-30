public class Write extends Instruction {

    public Write(Expression expr, String fl, int line, int col) {
        super(fl, line, col);
        this.lierExpression(expr);
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}