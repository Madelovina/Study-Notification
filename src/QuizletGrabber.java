import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class QuizletGrabber {

    private ArrayList<Term> site = new ArrayList<>();
    final private String TERM_TEXT = "SetPageTerm-wordText\"><span class=\"TermText notranslate lang-en\">";
    final private String DEF_TEXT = "SetPageTerm-definitionText\"><span class=\"TermText notranslate lang-en\">";

    public QuizletGrabber(String quiz) {
        try {
            URL url = new URL(quiz);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains(TERM_TEXT)) {
                    String[] ph = inputLine.split(TERM_TEXT);
                    for (int i = 1; i < ph.length; i++) {
                        Term phTerm = new Term(ph[i].substring(0, ph[i].indexOf("</span>")));
                        site.add(phTerm);
                    }
                    String[] ph2 = inputLine.split(DEF_TEXT);
                    for (int i = 1; i < ph2.length; i++) {
                        site.get(i - 1).setDef(ph2[i].substring(0, ph2[i].indexOf("</span>")));
                    }
                }
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Term returnRandom() {
        return site.get((int) (Math.random() * site.size()));
    }
}
