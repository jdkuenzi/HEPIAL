import java.util.Vector;
import java.io.FileReader;
import java_cup.runtime.Symbol;

public class hepialc {
    public static void main(String[] arg) {
        try {
        FileReader myFile = new FileReader(arg[0]);
        HepialLexer myLex = new HepialLexer(myFile);
        parser myP = new parser(myLex);
        try {
            // on lance le parsing, et on récupère l'AST
            DeclarationProgramme program = (DeclarationProgramme) myP.parse().value;
            
            if (program == null)
            // Aie, pas d'ASTreceived ....
            return;                
            AnalyseurSemantique analyseur = new AnalyseurSemantique();
            analyseur.visit(program);
            System.out.println("Analyse sémantique OK");
            GenerateurCode gc = new GenerateurCode();
            gc.visit(program);
            System.out.println("Génération du code jasmin OK");

            } catch (Exception e) {
                //le contenu du fichier est incorrect
                System.out.println("Parse error: " + e);
                e.printStackTrace();
            }
        }
        catch (Exception e){
            System.out.println("invalid file");
        }
    }
}
