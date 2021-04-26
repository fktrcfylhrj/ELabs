import java.util.ArrayList;

/**
 * Class SplitQuestionMark divides the text into question sentences
 *
 * @version 11.04 Apr 2021
 * @author Kochanov Alexander
 * */
public class TextHandler {
    private StringBuilder text;

    public TextHandler(String text) {
        this.text = new StringBuilder(text);
    }

    public StringBuilder getText() {
        return text;
    }

    public String[] getSentences(){
        return text.toString().split(".|!|\\?|\\.\\.\\.|\\?!");
    }

    public void setText(String text) {
        this.text.replace(0, this.text.length() - 1, text);
    }

    /**Removes 2 or more spaces or tabulation*/
    public void makeBeautiful(){
        String str = text.toString();

        str = str.replaceAll("  +|\\t+"," ");
        this.text.replace(0,text.length() - 1, str);
    }

    /**Divides the text into sentences according to split.
     * Returns a word selection (without repetitions) according to filter
     * */
    public ArrayList<Word> getWordsSelection(Filter filter, Split split){
        ArrayList<Word> selection= new ArrayList<>();
        ArrayList<String> sentences = split.split(text);

        for(int i = 0; i < sentences.size(); ++i){
            SentenceHandler sentence = new SentenceHandler(sentences.get(i));
            for(Word word : sentence.getWords()){
                if(filter.check(word) && !selection.contains(word)){
                    selection.add(word);
                }
            }
        }
        return selection;
    }
}
