import java.util.*;

public class TDS {

    private static TDS instance;
    private Map<String, Typebase> table;
    private Map<String, Typebase> constant;

    private TDS() {
        this.table = new HashMap<>();
        this.constant = new HashMap<>();
    }

    public static TDS getInstance() {
        if (instance == null) {
            return new TDS();
        }
        return instance;
    }

    public Typebase getType(String idf) {
        return this.table.get(idf);
    }

    public Typebase getConstantType(String idf) {
        return this.constant.get(idf);
    }

    public void put(String nom, Typebase t) {
        table.put(nom, t);
    }

    public void putConstant(String nom, Typebase t) {
        constant.put(nom, t);
    }

    public void putEmpty(String nom) {
        table.put(nom, null);
    }

    public Map<String, Typebase> getTable() {
        return this.table;
    }

    public Map<String, Typebase> getConstantTable() {
        return this.constant;
    }
}