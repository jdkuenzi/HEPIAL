public class TypeBooleen extends Typebase {

    public TypeBooleen(String name, String fl, int line, int col) {
        super(fl, line, col);
        this.name = name;
    }
    
    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}