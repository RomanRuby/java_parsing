package RM;

import java.util.List;

/**
 * @author Roman Nagibov
 */
public abstract class SearchEngine {

    private static final String USER_AGENT = "Mozilla/5.0 Chrome/58.0.3029.110";

    private final String userAgent;

    public SearchEngine() {
        this(USER_AGENT);
    }

    public SearchEngine(String userAgent) {
        this.userAgent = userAgent;
    }

    public List<Object> search(String arg) {
        String queryUri = getQueryUri(arg);
        String html = sendRequest(queryUri);
        return parse(html);
    }

    protected abstract List<Object> parse(String html);

    private String sendRequest() {

    }

    protected abstract String getQueryUri(String arg);

}
