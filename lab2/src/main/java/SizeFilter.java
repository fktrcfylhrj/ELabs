/**
 * Class SizeFilter checks the input word for size
 *
 * @version 11.04 Apr 2021
 * @author Kochanov Alexander
 * */
public class SizeFilter implements Filter{
    private int size;

    public SizeFilter(int size) {
        this.size = size;
    }

    @Override
    public boolean check(Word word) {
        return word.length() == size;
    }
}
