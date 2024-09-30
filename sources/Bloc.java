/*
 * Represent a group of instructions node inside the AST.
*/

import java.util.List;
import java.util.ArrayList;

public class Bloc extends Instruction {

    /**
     * The list of instructions
     */
    private List<Instruction> instructions;

    /**
     * Constructor with provided list
     */
    public Bloc(List<Instruction> instructions, String fl, int line, int col) {
        super(fl, line, col);
        this.setInstructions(instructions);
    }
    /**
     * Empty constructor
     */
    public Bloc(String fl, int line, int col) {
        this(new ArrayList<Instruction>(), fl, line, col);
    }

    /**
     * Get the list of instructions
     */
    public List<Instruction> getInstructions() {
        return this.instructions;
    }

    /**
     * Set the list of instructions
     */
    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }
    /**
     * Add an instruction to this bloc
     */
    public void addInstruction(Instruction instr) {
        this.instructions.add(instr);
    }

    /**
     * Get the size of instructions
     */
    public int size() {
        return this.instructions.size();
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}
