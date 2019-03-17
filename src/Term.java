public class Term {
    private String term;

    private String def;

    public Term(String t, String d) {
        term = t;
        def = d;
    }

    public Term(String t) {
        term = t;
        def = "";
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String toString() {
        return "Term: " + term + "\nDefinition: " + def + "\n";
    }
}
