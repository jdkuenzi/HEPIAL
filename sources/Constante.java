
public class Constante extends Instruction {

    private Typebase type;
    private Idf ident;
    private Expression expr;

    public Constante(Typebase type, Idf ident, Expression expr, String fl, int line, int col) {
        super(fl, line, col);
        this.type = type;
        this.ident = ident;
        this.expr = expr;
    }

    public Typebase getType() {
        return this.type;
    }

    public Idf getIdentifier() {
        return this.ident;
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