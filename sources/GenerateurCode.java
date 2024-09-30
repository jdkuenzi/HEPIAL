import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class GenerateurCode implements ASTVisitor {

    private PrintWriter jasminCode;
    private TDS tds;
    private int cpt;
    private Map<String, Integer> registres;

    public GenerateurCode() throws FileNotFoundException {
        jasminCode = new PrintWriter("monJasmin.j");
        registres = new HashMap<>();
        cpt = 0;
    }

    @Override
    public Object visit(Addition node) {
        node.getGauche().accept(this);
        node.getDroite().accept(this);
        jasminCode.println("iadd");
        return null;
    }

    @Override
    public Object visit(Affectation node) {
        node.getSource().accept(this);
        jasminCode.println("istore " + this.registres.get(((Idf)node.getDestination()).getNom()));
        return null;
    }

    @Override
    public Object visit(Bloc node) {
        for (Instruction i : node.getInstructions()) {
            i.accept(this);
        }
        return null;
    }

    @Override
    public Object visit(DeclarationProgramme node) {
        this.tds = node.getTDS();

        jasminCode.println(".class public " + node.getIdentifier().getNom());
        jasminCode.println(".super java/lang/Object");
        jasminCode.println(".method public static main([Ljava/lang/String;)V");
        jasminCode.println(".limit stack 20000");
        jasminCode.println(".limit locals 100");
        node.getDeclaration().accept(this);
        node.getInstructions().accept(this);
        jasminCode.println("return");
        jasminCode.println(".end method");

        jasminCode.close();
        return null;
    }

    @Override
    public Object visit(Egal node) {
        node.getGauche().accept(this);
        node.getDroite().accept(this);

        int lbl1 = this.getCpt();
        int lbl2 = this.getCpt();
        
        jasminCode.println("if_icmpeq label_" + lbl1);
        jasminCode.println("iconst_0");
        jasminCode.println("goto label_" + lbl2);
        jasminCode.println("label_" + lbl1 + ":");
        jasminCode.println("iconst_1");
        jasminCode.println("label_" + lbl2 + ":");
        
        return null;
    }

    @Override
    public Object visit(Idf node) {
        jasminCode.println("iload " + this.registres.get(node.getNom()));
        return null;
    }

    @Override
    public Object visit(Nombre node) {
        jasminCode.println("ldc " + node.getValeur());
        return null;
    }

    @Override
    public Object visit(Divide node) {
        node.getGauche().accept(this);
        node.getDroite().accept(this);
        jasminCode.println("idiv");
        return null;
    }

    @Override
    public Object visit(Diff node) {
        node.getGauche().accept(this);
        node.getDroite().accept(this);

        int lbl1 = this.getCpt();
        int lbl2 = this.getCpt();

        jasminCode.println("if_icmpne label_" + lbl1);
        jasminCode.println("iconst_0");
        jasminCode.println("goto label_" + lbl2);
        jasminCode.println("label_" + lbl1 + ":");
        jasminCode.println("iconst_1");
        jasminCode.println("label_" + lbl2 + ":");
        
        return null;
    }

    @Override
    public Object visit(Or node) {
        Parenthese left = (Parenthese)node.getGauche();
        if (left.getExpression() instanceof Relation) {
            left.getExpression().accept(this);
        }
        Parenthese right = (Parenthese)node.getDroite();
        if (right.getExpression() instanceof Relation) {
            right.getExpression().accept(this);
        }
        jasminCode.println("ior");
        return null;
    }

    @Override
    public Object visit(And node) {
        Parenthese left = (Parenthese)node.getGauche();
        if (left.getExpression() instanceof Relation) {
            left.getExpression().accept(this);
        }
        Parenthese right = (Parenthese)node.getDroite();
        if (right.getExpression() instanceof Relation) {
            right.getExpression().accept(this);
        }
        jasminCode.println("iand");
        return null;
    }

    @Override
    public Object visit(Tilda node) {
        node.getOperand().accept(this);
        jasminCode.println("ldc -1");
        jasminCode.println("ixor");
        return null;
    }

    @Override
    public Object visit(Not node) {
        node.getOperand().accept(this);

        int lbl1 = this.getCpt();
        int lbl2 = this.getCpt();
        
        jasminCode.println("ifeq label_" + lbl1);
        jasminCode.println("iconst_0");
        jasminCode.println("goto label_" + lbl2);
        jasminCode.println("label_" + lbl1 + ":");
        jasminCode.println("iconst_1");
        jasminCode.println("label_" + lbl2 + ":");
        
        return null;
    }

    @Override
    public Object visit(Minus node) {
        node.getGauche().accept(this);
        node.getDroite().accept(this);
        jasminCode.println("isub");
        return null;
    }

    @Override
    public Object visit(Times node) {
        node.getGauche().accept(this);
        node.getDroite().accept(this);
        jasminCode.println("imul");
        return null;
    }

    @Override
    public Object visit(Sup node) {
        node.getGauche().accept(this);
        node.getDroite().accept(this);
        int lbl1 = this.getCpt();
        int lbl2 = this.getCpt();

        
        jasminCode.println("if_icmpgt label_" + lbl1);
        jasminCode.println("iconst_0");
        jasminCode.println("goto label_" + lbl2);
        jasminCode.println("label_" + lbl1 + ":");
        jasminCode.println("iconst_1");
        jasminCode.println("label_" + lbl2 + ":");
        
        return null;
    }

    @Override
    public Object visit(Inf node) {
        node.getGauche().accept(this);
        node.getDroite().accept(this);

        int lbl1 = this.getCpt();
        int lbl2 = this.getCpt();
        
        jasminCode.println("if_icmplt label_" + lbl1);
        jasminCode.println("iconst_0");
        jasminCode.println("goto label_" + lbl2);
        jasminCode.println("label_" + lbl1 + ":");
        jasminCode.println("iconst_1");
        jasminCode.println("label_" + lbl2 + ":");
        
        return null;
    }

    @Override
    public Object visit(Infequal node) {
        node.getGauche().accept(this);
        node.getDroite().accept(this);

        int lbl1 = this.getCpt();
        int lbl2 = this.getCpt();
        
        jasminCode.println("if_icmple label_" + lbl1);
        jasminCode.println("iconst_0");
        jasminCode.println("goto label_" + lbl2);
        jasminCode.println("label_" + lbl1 + ":");
        jasminCode.println("iconst_1");
        jasminCode.println("label_" + lbl2 + ":");
        
        return null;
    }

    @Override
    public Object visit(Supequal node) {
        node.getGauche().accept(this);
        node.getDroite().accept(this);

        int lbl1 = this.getCpt();
        int lbl2 = this.getCpt();

        
        jasminCode.println("if_icmpge label_" + lbl1);
        jasminCode.println("iconst_0");
        jasminCode.println("goto label_" + lbl2);
        jasminCode.println("label_" + lbl1 + ":");
        jasminCode.println("iconst_1");
        jasminCode.println("label_" + lbl2 + ":");
        
        return null;
    }

    @Override
    public Object visit(Condition node) {
        node.getCondition().accept(this);

        int lbl1 = this.getCpt();
        int lbl2 = this.getCpt();
        
        jasminCode.println("ifeq label_" + lbl1);
        node.getThenInstruction().accept(this);
        jasminCode.println("goto label_" + lbl2);
        jasminCode.println("label_" + lbl1 + ":");
        if (node.hasElse()) {
            node.getElseInstruction().accept(this);
        }
        jasminCode.println("label_" + lbl2 + ":");
        
        return null;
    }

    @Override
    public Object visit(Write node) {
        jasminCode.println("getstatic java/lang/System/out Ljava/io/PrintStream;");
        node.getSource().accept(this);
        String typeVar = "";
        Idf ident;
        if (node.getSource() instanceof Idf) {
            ident = (Idf)node.getSource();
            Typebase t = this.tds.getType(ident.getNom());
            if (t == null) {
                t = this.tds.getConstantType(ident.getNom());
            }
            if (t.getClass() == TypeInteger.class) {
                typeVar = "I";
            } else {
                typeVar = "Z";
            }
        } else {
            typeVar = "Ljava/lang/String;";
        }
        
        jasminCode.println("invokevirtual java/io/PrintStream/println(" + typeVar + ")V");
        return null;
    }

    @Override
    public Object visit(Stringconst node) {
        jasminCode.println("ldc " + node.getValeur());
        return null;
    }

    @Override
    public Object visit(Read node) {
        jasminCode.println("new java/util/Scanner");
        jasminCode.println("dup");
        jasminCode.println("getstatic java/lang/System/in Ljava/io/InputStream;");
        jasminCode.println("invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V");
        jasminCode.println("invokevirtual java/util/Scanner/nextInt()I");
        jasminCode.println("istore " + this.registres.get(node.getIdentifier().getNom()));
        return null;
    }

    @Override
    public Object visit(Pour node) {
        int lbl1 = this.getCpt();
        int lbl2 = this.getCpt();

        node.getBorneInf().accept(this);
        jasminCode.println("istore " + this.registres.get(node.getIdentifier().getNom()));
        jasminCode.println("label_" + lbl1 + ":");
        node.getBorneSup().accept(this);
        jasminCode.println("iload " + this.registres.get(node.getIdentifier().getNom()));
        jasminCode.println("if_icmplt label_" + lbl2);
        node.getInstructions().accept(this);
        jasminCode.println("iload " + this.registres.get(node.getIdentifier().getNom()));
        jasminCode.println("ldc 1");
        jasminCode.println("iadd");
        jasminCode.println("istore " + this.registres.get(node.getIdentifier().getNom()));
        jasminCode.println("goto label_" + lbl1);
        jasminCode.println("label_" + lbl2 + ":");
        return null;
    }

    @Override
    public Object visit(TypeInteger node) {
        return null;
    }

    @Override
    public Object visit(TypeBooleen node) {
        return null;
    }

    @Override
    public Object visit(TantQue node) {
        
        int lbl1 = this.getCpt();
        int lbl2 = this.getCpt();
        
        jasminCode.println("label_" + lbl1 + ":");
        node.getCondition().accept(this);
        jasminCode.println("ifeq label_" + lbl2);
        node.getInstructions().accept(this);
        jasminCode.println("goto label_" + lbl1);
        jasminCode.println("label_" + lbl2 + ":");
        return null;
    }

    @Override
    public Object visit(Constante node) {
        int lbl1 = this.getCpt();
        String str = ".var " + lbl1 + " is " + node.getIdentifier().getNom() + " " + (this.tds.getConstantType(node.getIdentifier().getNom()) instanceof TypeInteger ? "I" : "Z");
        jasminCode.println(str);
        node.getExpression().accept(this);
        jasminCode.println("istore " + lbl1);
        this.registres.put(node.getIdentifier().getNom(), lbl1);
        return null;
    }

    @Override
    public Object visit(Variable node) {
        for (Idf i : node.getListIdentifier()) {
            int lbl1 = this.getCpt();
            String str = ".var " + lbl1 + " is " + i.getNom() + " " + (this.tds.getType(i.getNom()) instanceof TypeInteger ? "I" : "Z");
            this.registres.put(i.getNom(), lbl1);
            jasminCode.println(str);
        }
        return null;
    }

    @Override
    public Object visit(True node) {
        jasminCode.println("ldc 1"); 
        return null;
    }

    @Override
    public Object visit(False node) {
        jasminCode.println("ldc 0");
        return null;
    }

    @Override
    public Object visit(Parenthese node) {
        node.getExpression().accept(this);
        return null;
    }

    private int getCpt() {
        this.cpt++;
        return this.cpt;
    }
}