import java.util.*;

public class AnalyseurSemantique implements ASTVisitor {

    TDS tds;

    @Override
    public Object visit(Addition node) {
        Object gauche = node.getGauche().accept(this);
        Object droite = node.getDroite().accept(this);
        if (gauche.getClass() != TypeInteger.class || droite.getClass() != TypeInteger.class) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Addition : Impossible d'aditionner "
                + gauche.getClass() + " + " + droite.getClass() + " à la ligne " + node.getLine()
            );
        }
        return new TypeInteger("entier", "", 0, 0);
    }

    @Override
    public Object visit(Affectation node) {
        Object gauche = node.getDestination().accept(this);
        Object droite = node.getSource().accept(this);
        if (TDS.getInstance().getConstantTable().containsKey(((Idf)node.getDestination()).getNom())) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Affectation : Impossible d'affecter une constante à la ligne " 
                + node.getLine()
            );
        }
        if (gauche.getClass() != droite.getClass()) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Affectation : Impossible d'affecter le type"
                + gauche.getClass() + " = " + droite.getClass() + " à la ligne " + node.getLine()
            );
        }
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
        node.getDeclaration().accept(this);
        node.getIdentifier().accept(this);
        node.getInstructions().accept(this);
        return null;
    }

    @Override
    public Object visit(Egal node) {
        Object gauche = node.getGauche().accept(this);
        Object droite = node.getDroite().accept(this);
        if (gauche.getClass() != droite.getClass()) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Egal : Impossible de comparer "
                + gauche.getClass() + " == " + droite.getClass() + " à la ligne " + node.getLine() 
            );
        }
        return new TypeBooleen("booleen", "", 0, 0);
    }

    @Override
    public Object visit(Idf node) {
        Typebase monType = tds.getType(node.getNom());
        if (monType == null) {
            boolean flag = tds.getTable().containsKey(node.getNom());
            if (!flag) {
                monType = tds.getConstantType(node.getNom());
                if (monType == null) {
                    throw new RuntimeException(
                        "Erreur dans l'analyseur sémantique Idf : Identificateur introuvable à la ligne "
                        + node.getLine()
                    );    
                }
            }
        }

        if (monType instanceof TypeInteger) {
            return ((TypeInteger) monType);
        }
        if (monType instanceof TypeBooleen) {
            return ((TypeBooleen) monType);
        }
        return null;
    }

    @Override
    public Object visit(Nombre node) {
        return new TypeInteger("entier", "", 0, 0);
    }

    @Override
    public Object visit(Divide node) {
        Object gauche = node.getGauche().accept(this);
        Object droite = node.getDroite().accept(this);
        if (gauche.getClass() != TypeInteger.class || droite.getClass() != TypeInteger.class) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Divide : Impossible de diviser le type "
                + gauche.getClass() + " / " + droite.getClass() + " à la ligne " + node.getLine()
            );
        }
        return new TypeInteger("entier", "", 0, 0);
    }

    @Override
    public Object visit(Diff node) {
        Object gauche = node.getGauche().accept(this);
        Object droite = node.getDroite().accept(this);
        if (gauche.getClass() != droite.getClass()) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Diff : Impossible de comparer le type "
                + gauche.getClass() + " != " + droite.getClass() + " à la ligne " + node.getLine()
            );
        }
        return new TypeBooleen("booleen", "", 0, 0);
    }

    @Override
    public Object visit(Or node) {
        Object gauche = node.getGauche().accept(this);
        Object droite = node.getDroite().accept(this);
        if (gauche.getClass() != TypeBooleen.class && droite.getClass() != TypeBooleen.class) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Or : Impossible de comparer le type "
                + gauche.getClass() + " or " + droite.getClass() + " à la ligne " + node.getLine()
            );
        }
        return new TypeBooleen("booleen", "", 0, 0);
    }

    @Override
    public Object visit(And node) {
        Object gauche = node.getGauche().accept(this);
        Object droite = node.getDroite().accept(this);
        if (gauche.getClass() != TypeBooleen.class && droite.getClass() != TypeBooleen.class) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique And : Impossible de comparer le type "
                + gauche.getClass() + " and " + droite.getClass() + " à la ligne " + node.getLine()
            );
        }
        return new TypeBooleen("booleen", "", 0, 0);
    }

    @Override
    public Object visit(Tilda node) {
        Object op = node.getOperand().accept(this);
        if (op.getClass() != TypeInteger.class) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Tilda : L'opérateur ~ ne s'applique pas sur le type"
                + op.getClass() + " à la ligne " + node.getLine()
            );
        }
        return new TypeInteger("booleen", "", 0, 0);
    }

    @Override
    public Object visit(Not node) {
        Object op = node.getOperand().accept(this);
        if (op.getClass() != TypeBooleen.class) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Not : L'opérateur !(non) ne s'applique pas sur le type"
                + op.getClass() + " à la ligne " + node.getLine()
            );
        }
        return new TypeBooleen("booleen", "", 0, 0);
    }

    @Override
    public Object visit(Minus node) {
        Object gauche = node.getGauche().accept(this);
        Object droite = node.getDroite().accept(this);
        if (gauche.getClass() != TypeInteger.class || droite.getClass() != TypeInteger.class) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Minus : Impossible de soustraire le type "
                + gauche.getClass() + " - " + droite.getClass() + " à la ligne " + node.getLine()
            );
        }
        return new TypeInteger("entier", "", 0, 0);
    }

    @Override
    public Object visit(Times node) {
        Object gauche = node.getGauche().accept(this);
        Object droite = node.getDroite().accept(this);
        if (gauche.getClass() != TypeInteger.class || droite.getClass() != TypeInteger.class) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Times : Impossible de multiplier le type "
                + gauche.getClass() + " * " + droite.getClass() + " à la ligne " + node.getLine()
            );
        }
        return new TypeInteger("entier", "", 0, 0);
    }

    @Override
    public Object visit(Sup node) {
        Object gauche = node.getGauche().accept(this);
        Object droite = node.getDroite().accept(this);
        if (gauche.getClass() != TypeInteger.class || droite.getClass() != TypeInteger.class) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Sup : Impossible de soustraire le type "
                + gauche.getClass() + " > " + droite.getClass() + " à la ligne " + node.getLine()
            );
        }
        return new TypeBooleen("booleen", "", 0, 0);
    }

    @Override
    public Object visit(Inf node) {
        Object gauche = node.getGauche().accept(this);
        Object droite = node.getDroite().accept(this);
        if (gauche.getClass() != TypeInteger.class || droite.getClass() != TypeInteger.class) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Inf : Impossible de soustraire le type "
                + gauche.getClass() + " < " + droite.getClass() + " à la ligne " + node.getLine()
            );
        }
        return new TypeBooleen("booleen", "", 0, 0);
    }

    @Override
    public Object visit(Infequal node) {
        Object gauche = node.getGauche().accept(this);
        Object droite = node.getDroite().accept(this);
        if (gauche.getClass() != TypeInteger.class || droite.getClass() != TypeInteger.class) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Infequal : Impossible de soustraire le type "
                + gauche.getClass() + " <= " + droite.getClass() + " à la ligne " + node.getLine()
            );
        }
        return new TypeBooleen("booleen", "", 0, 0);
    }

    @Override
    public Object visit(Supequal node) {
        Object gauche = node.getGauche().accept(this);
        Object droite = node.getDroite().accept(this);
        if (gauche.getClass() != TypeInteger.class || droite.getClass() != TypeInteger.class) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Supequal : Impossible de soustraire le type "
                + gauche.getClass() + " >= " + droite.getClass() + " à la ligne " + node.getLine()
            );
        }
        return new TypeBooleen("booleen", "", 0, 0);
    }

    @Override
    public Object visit(Condition node) {
        node.getCondition().accept(this);
        node.getThenInstruction().accept(this);
        if (node.hasElse()) {
            node.getElseInstruction().accept(this);
        }
        return null;
    }

    @Override
    public Object visit(Write node) {
        node.getSource().accept(this);
        return null;
    }

    @Override
    public Object visit(Stringconst node) {
        return null;
    }

    @Override
    public Object visit(Read node) {
        node.getIdentifier().accept(this);
        return null;
    }

    @Override
    public Object visit(Pour node) {
        Object type = node.getIdentifier().accept(this);
        if (type.getClass() != TypeInteger.class) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Pour : Impossible que l'identificateur soit de type "
                + type.getClass() + " à la ligne " + node.getLine()
            );
        }
        Expression borneInf = node.getBorneInf();
        Expression borneSup = node.getBorneSup();
        if ((borneInf.getClass() != Nombre.class && borneInf.getClass() != Idf.class) || (borneSup.getClass() != Nombre.class && borneSup.getClass() != Idf.class)) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Pour : Impossible que la borne inférieure et la borne supérieure soient de type "
                + borneInf.getClass() + " et " + borneSup.getClass() + " à la ligne " + node.getLine()
            );
        }
        node.getInstructions().accept(this);
        return null;
    }

    @Override
    public Object visit(TypeInteger node) {
        Typebase v = tds.getInstance().getType(node.getType());
        if (v.getClass() != TypeInteger.class) {
            throw new RuntimeException("Erreur dans l'analyseur sémantique TypeInteger");
        }
        return node;
    }

    @Override
    public Object visit(TypeBooleen node) {
        Typebase v = tds.getInstance().getType(node.getType());
        if (v.getClass() != TypeBooleen.class) {
            throw new RuntimeException("Erreur dans l'analyseur sémantique TypeBooleen");
        }
        return node;
    }

    @Override
    public Object visit(TantQue node) {
        node.getCondition().accept(this);
        node.getInstructions().accept(this);
        return null;
    }

    @Override
    public Object visit(Constante node) {
        Typebase type = node.getType();
        Object value = node.getExpression().accept(this);
        if (type.getClass() != value.getClass()) {
            throw new RuntimeException(
                "Erreur dans l'analyseur sémantique Constante : Impossible d'affecter une constante de type "
                + type.getClass() + " avec une valeur de type " + value.getClass() + " à la ligne " + node.getLine()
            );            
        }
        return null;
    }

    @Override
    public Object visit(Variable node) {
        for (Idf v : node.getListIdentifier()) {
            this.visit(v);
        }
        return null;
    }

    @Override
    public Object visit(True node) {
        return new TypeBooleen("booleen", "", 0, 0);
    }

    @Override
    public Object visit(False node) {
        return new TypeBooleen("booleen", "", 0, 0);
    }

    @Override
    public Object visit(Parenthese node) {
        Object par = node.getExpression().accept(this);
        return par;
    }
}