package library;
import java.util.*;
public class TitleComparator implements Comparator<Document> {

    @Override
    public int compare(Document d1,Document d2) {
        return d1.getTitle().compareTo(d2.getTitle());
    }
}
