public class TantQue extends Instruction {

    private Expression cond;
    private Bloc instr;

    public TantQue(Expression cond, Bloc instr, String fl, int line, int col) {
        super(fl, line, col);
        this.cond = cond;
        this.instr = instr;
    }

    public Expression getCondition() {
        return this.cond;
    }

    public Bloc getInstructions() {
        return this.instr;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}