CLASSPATH=java-cup-11a.jar:.
SRCFLEX=hepial.flex
LEXER=HepialLexer
SRCCUP=hepial.cup
OBJ=sym.class parser.class HepialLexer.class hepialc.class

FILE=hepialc ../"Exemple Code Hepial"/demo3.hepial
JASMIN=monJasmin.j

all : $(OBJ) generateCode jasmin

generateCode:
	java -classpath $(CLASSPATH) $(FILE);

jasmin :
	java -jar jasmin.jar $(JASMIN)

HepialLexer.java : $(SRCFLEX)
	jflex $(SRCFLEX)

sym.java parser.java : $(SRCCUP)
	java -jar java-cup-11a.jar $(SRCCUP)

%.class : %.java
	javac -classpath $(CLASSPATH) $<

clean:
	rm -rf *.class HepialLexer.java sym.java parser.java monJasmin.j