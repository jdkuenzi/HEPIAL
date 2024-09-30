import java.time.Year;
import java.util.List;

public class Variable extends Instruction {

    private Typebase type;
    private List<Idf> ident;

    public Variable(Typebase type, List<Idf> ident, String fl, int line, int col) {
        super(fl, line, col);
        this.type = type;
        this.ident = ident;
    }

    public List<Idf> getListIdentifier() {
        return this.ident;
    }

    public Typebase getType() {
        return this.type;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}