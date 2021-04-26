import java.util.ArrayList;

/**
 * Class Main demonstrates, how program works
 *
 * @version 11.04 Apr 2021
 * @author Kochanov Alexander
 * */
public class Main {
    public static void main(String[] args){
        String path = "E:\\EPAM_LABS\\lab2\\src\\main\\java\\book.txt";
        TextHandler handler = new TextHandler(TextReader.readFile(path));
        int wordSize = 3;

        handler.makeBeautiful();
        SplitQuestionMark splitter = new SplitQuestionMark();
        SizeFilter filter = new SizeFilter(wordSize);
        ArrayList<Word> words = handler.getWordsSelection(filter, splitter);

        for(Word word : words){
            System.out.println(word.getWord());
        }
    }
}