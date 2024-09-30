public class Stringconst extends Expression {
    /**
     * Value contained in this number node
     */
    private String valeur;

    /**
     * Constructor
     */
    public Stringconst(String val, String fl, int line, int col) {
        super(fl, line, col);
        this.valeur = val;
    }

    /**
     * Get the node value
     */
    public String getValeur() {
        return this.valeur;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}
