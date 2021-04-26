import java.util.ArrayList;

/**
 * Interface Filter checks the input word for compliance with some template
 *
 * @version 11.04 Apr 2021
 * @author Kochanov Alexander
 * */
public class SentenceHandler {
    private String sentence;

    public SentenceHandler(String sentence) {
        this.sentence = sentence.trim();
    }

    public ArrayList<Word> getWords(){
        String[] words = sentence.split(" |, | - |: |\\?|\\.|!|;");
        ArrayList<Word> arrayListWords= new ArrayList<>();
        for (int i = 0; i < words.length; ++i){
            arrayListWords.add(new Word(words[i]));
        }
        return arrayListWords;
    }
}
