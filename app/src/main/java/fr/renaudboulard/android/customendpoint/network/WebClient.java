package fr.renaudboulard.android.customendpoint.network;

import java.util.ArrayList;

import fr.renaudboulard.android.customendpoint.model.User;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by renaud on 24/08/15.
 */
public interface WebClient {

    @GET("/users")
    void user(Callback<ArrayList<User>> callback);
}
