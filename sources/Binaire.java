/*
 * Base class that represent a binary expression node inside the AST.
*/

public abstract class Binaire extends Expression {
    /**
     * The expression at its left
     */
    protected Expression operandeGauche;
    /**
     * The expression at its right
     */
    protected Expression operandeDroite;

    /**
     * Constructor
     */
    public Binaire(String fl, int line, int col) {
        super(fl, line, col);
    }

    /**
     * Get the left expression
     */
    public Expression getGauche() {
        return this.operandeGauche;
    }
    /**
     * Get the right expression
     */
    public Expression getDroite() {
        return this.operandeDroite;
    }

    /**
     * Set the left expression
     */
    public void lierGauche(Expression exp) {
        this.operandeGauche = exp;
    }
    /**
     * Set the right expression
     */
    public void lierDroit(Expression exp) {
        this.operandeDroite = exp;
    }
}
