import java.util.*;

/*
 * Represent a function declaration instruction node inside the AST.
*/

public class DeclarationProgramme extends Instruction {
    /**
     * The declared variable identifier
     */
    protected Idf identifier;
    /**
     * The declaration section of the function (list of DeclarationVariable)
     */
    protected Bloc declarations;
    /**
     * The body of the function
     */
    protected Bloc instructions;

    /**
     * Table of symbol
     */
    protected TDS tds;

    /**
     * Constructor
     */
    public DeclarationProgramme(Idf identifier, String fl, int line, int col){
        super(fl, line, col);
        this.identifier = identifier;
    }

    public void setTDS(TDS tds) {
        this.tds = tds;
    }

    public TDS getTDS() {
        return this.tds;
    }

    /**
     * Get the declared variable identifier
     */
    public Idf getIdentifier() {
        return this.identifier;
    }
    /**
     * Get the declaration section of the function
     */
    public Bloc getDeclaration() {
        return this.declarations;
    }
    /**
     * Get the body of the function
     */
    public Bloc getInstructions() {
        return this.instructions;
    }

    /**
     * Set the declared variable identifier
     */
    public void setIdentifier(Idf identifier) {
        this.identifier = identifier;
    }
    /**
     * Set the declarations section of the function
     */
    public void setDeclarations(Bloc declarations) {
        this.declarations = declarations;
    }
    /**
     * Set the body of the function
     */
    public void setInstructions(Bloc instructions) {
        this.instructions = instructions;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}
