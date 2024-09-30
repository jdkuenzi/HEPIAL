public class Pour extends Instruction {

    private Idf idf;
    private Expression borneInf;
    private Expression borneSup;
    private Bloc instr;

    public Pour(Idf idf, Expression borneInf, Expression borneSup, Bloc instr, String fl, int line, int col) {
        super(fl, line, col);
        this.idf = idf;
        this.borneInf = borneInf;
        this.borneSup = borneSup;
        this.instr = instr;
    }

    public String getIteratorName() {
        return this.idf.getNom();
    }

    public Idf getIdentifier() {
        return this.idf;
    }

    public Expression getBorneInf() {
        return this.borneInf;
    }

    public Expression getBorneSup() {
        return this.borneSup;
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