package fr.renaudboulard.android.customendpoint.network;

import fr.renaudboulard.android.customendpoint.URLInterface;
import retrofit.Endpoint;

/**
 * Created by renaud on 24/08/15.
 */
public class CustomEndPoint implements Endpoint {

    private String url;
    private URLInterface urlInterface;

    public CustomEndPoint(URLInterface urlInterface) {
        this.urlInterface = urlInterface;
    }

    @Override
    public String getUrl() {
        url = urlInterface.getCustomUrl();
        if (url == null) {
            throw new IllegalStateException("Illegal URL.");
        }
        return url;
    }

    @Override
    public String getName() {
        return null;
    }
}
