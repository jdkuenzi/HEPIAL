public abstract class Unaire extends Expression {
    /**
     * The expression at its right
     */
    protected Expression operande;

    /**
     * Constructor
     */
    public Unaire(String fl, int line, int col) {
        super(fl, line, col);
    }

    /**
     * Get the right expression
     */
    public Expression getOperand() {
        return this.operande;
    }

    /**
     * Set the right expression
     */
    public void lier(Expression exp) {
        this.operande = exp;
    }
}