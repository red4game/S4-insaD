/**
 * TP5 : Collections
 */
package library;

import java.util.*;

/**
 * Library consisting of a collection of documents
 * @author ? ?
 * @author ? ?
 * @version 2015.02.27
 */
public class Library {

    /**
     * The document collection
     */
    private Map<Integer,Document> documents;

    private Map<String,ArrayList<Document>> documentsByAuthor;

    /**
     * Constructor
     */
    public Library(){
        documents = new HashMap<Integer,Document>();
        documentsByAuthor = new HashMap<String,ArrayList<Document>>();
    }

    /**
     * Constructor
     */
    public Library(Collection<Document> c){
        documents = new HashMap<Integer,Document>();
        documentsByAuthor = new HashMap<String,ArrayList<Document>>();
        for (Document d:c){
            add(d);
        }
    }

    /**
     * Gives the size of the library
     * @return the number of documents in the library
     */
    public int size(){
        return documents.size();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString(){
        return documents.toString();
    }

    public String toString(Comparator<Document> c){
        List<Document> l = new ArrayList<Document>(documents.values());
        l.sort(c);
        return l.toString();
    }



    /**
     * Add a document to the library
     * @param doc the document to add
     */
    public void add(Document doc){
        documents.put(doc.getBarCode(),doc);
        for (String author : doc.getAuthors()) {
            if (!documentsByAuthor.containsKey(author))
                documentsByAuthor.put(author, new ArrayList<Document>());
            if (!documentsByAuthor.get(author).contains(doc))
                documentsByAuthor.get(author).add(doc);
        }
    }

    /**
     * Borrow a document from the library
     * @param barCode the bar code of the document to borrow
     * @return the borrowed document
     * @version 1
     */
    public Document borrow(int barCode){
        Document mydoc = documents.get(barCode);
        if (mydoc != null) {
            mydoc.setAvailable(false);
        }
        return mydoc;
    }
    //0.0 to run BorrowEfficiency

    /**
     * Return a document to the library
     * @param barCode the bar code of the document to return
     * @version 1
     */
    public void takeBack(int barCode){
        Document mydoc = documents.get(barCode);
        if (mydoc != null){
            mydoc.setAvailable(true);
        }
    }

    public ArrayList<Document> getDocumentByAuthor(String author){
        if (!(documentsByAuthor.containsKey(author))) {
            return new ArrayList<Document>();
        }
        return documentsByAuthor.get(author);
        //TODO : ajouter des tests junits
    }
}
