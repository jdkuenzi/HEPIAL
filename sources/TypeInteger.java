public class TypeInteger extends Typebase {

    public TypeInteger(String name, String fl, int line, int col) {
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