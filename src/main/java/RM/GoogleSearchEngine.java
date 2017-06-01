package RM;

/**
 * @author Roman Nagibov
 */
public class GoogleSearchEngine extends SearchEngine {

    private static final String SEARCH_GOOGLE = "http://www.google.com/search?q=";

    public GoogleSearchEngine() {
        super(SEARCH_GOOGLE);
    }

}
