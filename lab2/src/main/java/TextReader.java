import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

/**
 * Class TextReader reads the text and returns it
 *
 * @version 11.04 Apr 2021
 * @author Kochanov Alexander
 * */
public class TextReader {
    /**Returns text from file fileName*/
    public static String readFile(String fileName) {
        BufferedReader br = null;
        StringBuilder text = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(fileName));
            String tmp;
            while ((tmp = br.readLine()) != null) {
                text.append(tmp);
            }
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            if(br != null){
                try{
                    br.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        return text.toString();
    }
}


