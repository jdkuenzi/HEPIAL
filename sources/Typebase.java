public abstract class Typebase extends Instruction {

    protected String name;

    public Typebase(String fl, int line, int col) {
        super(fl, line, col);
    }

    public String getType() {
        return this.name;
    }
}