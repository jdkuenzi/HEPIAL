
/*
 * Base class that represent an instruction node inside the AST.
*/

public abstract class Instruction extends ASTNode {

    protected Expression expr;
    /**
     * Constructor
     */
    public Instruction(String fl, int line, int col) {
        super(fl, line, col);
    }

    public void lierExpression(Expression expr) {
        this.expr = expr;
    }

    public Expression getSource() {
        return this.expr;
    }
}
