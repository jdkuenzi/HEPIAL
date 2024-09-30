
public class Parenthese extends Expression {
    
    protected Expression expr;

    public Parenthese(Expression expr, String fl, int line, int col) {
        super(fl, line, col);
        this.expr = expr;
    }

    public Expression getExpression() {
        return this.expr;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}