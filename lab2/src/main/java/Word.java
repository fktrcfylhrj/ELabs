import java.util.Objects;

/**
 * Class Word provides word handling
 *
 * @version 11.04 Apr 2021
 * @author Kochanov Alexander
 * */
public class Word {
    private String word;

    public Word(String word) {
        if(Checker.checkWord(word)){
            this.word = word;
        } else{
            this.word = null;
        }
    }

    public String getWord() {
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word1 = (Word) o;
        return Objects.equals(getWord(), word1.getWord());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWord());
    }

    public int length(){
       if(word == null){
           return 0;
       }
       return  word.length();
    }
}
