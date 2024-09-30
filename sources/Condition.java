public class Condition extends Instruction {

    protected Expression condition;

    protected Bloc alors;

    protected Bloc sinon;

    protected boolean hasElse;

    public Condition(Expression cond, Bloc alors, Bloc sinon, String fl, int line, int col) {
        super(fl, line, col);
        this.condition = cond;
        this.alors = alors;
        this.sinon = sinon;
        this.hasElse = true;
    }

    public Condition(Expression cond, Bloc alors, String fl, int line, int col) {
        super(fl, line, col);
        this.condition = cond;
        this.alors = alors;
        this.hasElse = false;
    }

    public Expression getCondition() {
        return this.condition;
    }

    public Bloc getThenInstruction() {
        return this.alors;
    }

    public Bloc getElseInstruction() {
        return this.sinon;
    }

    public boolean hasElse() {
        return this.hasElse;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}