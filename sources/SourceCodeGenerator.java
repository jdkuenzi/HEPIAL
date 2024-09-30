/*
 * Source code generator that walks the AST and generates code source from the AST
 *
 * @author Stephane Malandain 
 */

import java.util.*;

public class SourceCodeGenerator implements ASTVisitor{

    Map<String, Typebase> tds = new HashMap<>();

    /**
     * Generated code
     */
    private String code = "";
    /**
     * Depth level (tabulations)
     */
    private int level = 0;

    public SourceCodeGenerator() {

    }
    /**
     * If we are currently declaring function parameters
     */
   //  private boolean isParameterDeclaration = false;

    /**
     * Adds tabulation with current level
     */
    private void addTabulation(){
        addTabulation(level);
    }

    /**
     * Adds tabulation with spacified level
     */
    private void addTabulation(int level){
        for (int i = 0;i < level; i++)
            code += "    ";
    }

    public Object visit(Addition node){
        node.getGauche().accept(this);
        code += " + ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Affectation node){
        node.getDestination().accept(this);
        code += " = ";
        node.getSource().accept(this);
        code += ";";
        return null;
    }

    public Object visit(Bloc node){
        for (Instruction inst: node.getInstructions()){
            code += "\n";
            addTabulation();
            inst.accept(this);
        }
        return null;
    }

    public Object visit(Stringconst node){
        code += node.getValeur();
        return null;
    }

   public Object visit(Condition node){
        code += "si ";
        node.getCondition().accept(this);
        code += " alors";
        level += 1;
        node.getThenInstruction().accept(this);
        if (node.hasElse()){
            code += "\n";
            addTabulation(level - 1);
            code += "sinon";
            node.getElseInstruction().accept(this);
        }
        level -= 1;
        code += "\n";
        addTabulation();
        code += "finsi";
        return null;
    }

    public Object visit(Constante node){
        code += "constante ";

        // Typebase type = TDS.getInstance().getType(node.getIdentifier().getNom());
        node.getType().accept(this);
        code += " ";
        node.getIdentifier().accept(this);
        code += " = ";
        node.getExpression().accept(this);
        code += ";";
        return null;
    }

    public Object visit(DeclarationProgramme node){
        /* TDS.getInstance().resetBlocNumber();
        
        TDS.getInstance().entreeBloc();
        level += 1;
        */
        this.tds = node.table;
        code += "programme ";
        node.getIdentifier().accept(this);
        node.getDeclaration().accept(this);
        code += "\ndebutprg";
        node.getInstructions().accept(this);
        code += "\nfinprg";

        /*
        TDS.getInstance().sortieBloc();
        level -= 1;
        */
        return null;
    }

   public Object visit(Variable node){
       node.getType().accept(this);
        //    for (Map.Entry mapentry : this.tds.entrySet()) {
        //         System.out.println("clé: "+mapentry.getKey() 
        //                             + " | valeur: " + ((Typebase) mapentry.getValue()).getType());
        //     }
       for (Idf v : node.getListIdentifier()) {
            code += " ";
            v.accept(this);
            code += ";";
        }
        return null;
    }

   public Object visit(Diff node){
        node.getGauche().accept(this);
        code += " <> ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Divide node){
        node.getGauche().accept(this);
        code += " / ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Write node){
        code += "ecrire ";
        node.getSource().accept(this);
        code += ";";
        return null;
    }

    public Object visit(Egal node){
        node.getGauche().accept(this);
        code += " == ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(And node){
        node.getGauche().accept(this);
        code += " et ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(False node){
        code += "faux";
        return null;
    }

    public Object visit(Idf node){
        code += node.getNom();
        return null;
    }

    public Object visit(Infequal node){
        node.getGauche().accept(this);
        code += " <= ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Inf node){
        node.getGauche().accept(this);
        code += " < ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Read node){
        code += "lire ";
        node.getSource().accept(this);
        code += ";";
        return null;
    }

    // public Object visit(Minus node){
    //     code += "-";
    //     // node.getOperand().accept(this);
    //     return null;
    // }

    public Object visit(Nombre node){
        code += Integer.toString(node.getValeur());
        return null;
    }

    public Object visit(Not node){
        code += "non ";
        // node.getOperand().accept(this);
        return null;
    }

    public Object visit(Or node){
        node.getGauche().accept(this);
        code += " ou ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Parenthese node){
        code += "(";
        node.getExpression().accept(this);
        code += ")";
        return null;
    }

    public Object visit(Pour node){
        code += "pour ";
        node.getIdentifier().accept(this);
        code += " allantde ";
        node.getBorneInf().accept(this);
        code += " a ";
        node.getBorneSup().accept(this);
        code+= " faire";
        level += 1;
        node.getInstructions().accept(this);
        level -= 1;
        code += "\n";
        addTabulation();
        code += "finpour";
        return null;
    }

    public Object visit(Times node){
        node.getGauche().accept(this);
        code += " * ";
        node.getDroite().accept(this);
        return null;
    }

    // public Object visit(Retour node){
    //     code += "retourne ";
    //     node.getSource().accept(this);
    //     code += ";";
    //     return null;
    // }

    public Object visit(Minus node){
        node.getGauche().accept(this);
        code += " - ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Supequal node){
        node.getGauche().accept(this);
        code += " >= ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Sup node){
        node.getGauche().accept(this);
        code += " > ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(TantQue node){
        code += "tantque ";
        node.getCondition().accept(this);
        code += " faire";
        level += 1;
        node.getInstructions().accept(this);
        level -= 1;
        code += "\n";
        addTabulation();
        code += "fintantque";
        return null;
    }

    public Object visit(Tilda node){
        code += "~";
        node.getOperand().accept(this);
        return null;
    }

    public Object visit(True node){
        code += "vrai";
        return null;
    }

    public Object visit(TypeBooleen node) {
        code += node.getType();
        return null;
    }

    public Object visit(TypeInteger node) {
        code += node.getType();
        return null;
    }

    public String getCode(){
        return code;
    }
}
