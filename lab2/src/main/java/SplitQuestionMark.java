import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class SplitQuestionMark divides the text into question sentences
 *
 * @version 11.04 Apr 2021
 * @author Kochanov Alexander
 * */
public class SplitQuestionMark implements Split{

    @Override
    public ArrayList<String> split(StringBuilder text) {
        ArrayList<String> sentences = new ArrayList<>();
        ArrayList<String> qSentences = new ArrayList<>();
        Pattern pattern = Pattern.compile(".+?[\\.\\?!]");
        Matcher matcher = pattern.matcher(text);

        while(matcher.find()){
            sentences.add(text.substring(matcher.start(),matcher.end()));
        }
        for (int i = 0; i<sentences.size(); ++i){
            if(sentences.get(i).lastIndexOf("?") == sentences.get(i).length() - 1){
                qSentences.add(sentences.get(i));
            }
        }
        return qSentences;
    }
}
