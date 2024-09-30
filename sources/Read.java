public class Read extends Instruction {

    protected Idf identifier;

    public Read(Idf identifier, String fl, int line, int col) {
        super(fl, line, col);
        this.identifier = identifier;
    }

    public Idf getIdentifier() {
        return this.identifier;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}