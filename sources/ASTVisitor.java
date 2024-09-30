/*
 * AST visiteur interface
*/

public interface ASTVisitor {
    Object visit(Addition node);
    Object visit(Affectation node);
    Object visit(Bloc node);
    Object visit(DeclarationProgramme node);
    Object visit(Egal node);
    Object visit(Idf node);
    Object visit(Nombre node);
    Object visit(Divide node);
    Object visit(Diff node);
    Object visit(Or node);
    Object visit(And node);
    Object visit(Tilda node);
    Object visit(Not node);
    Object visit(Minus node);
    Object visit(Times node);
    Object visit(Sup node);
    Object visit(Inf node);
    Object visit(Infequal node);
    Object visit(Supequal node);
    Object visit(Condition node);
    Object visit(Write node);
    Object visit(Stringconst node);
    Object visit(Read node);
    Object visit(Pour node);
    Object visit(TypeInteger node);
    Object visit(TypeBooleen node);
    Object visit(TantQue node);
    Object visit(Constante node);
    Object visit(Variable node);
    Object visit(True node);
    Object visit(False node);
    Object visit(Parenthese node);
}
