package fr.renaudboulard.android.customendpoint.network;

import java.util.List;

import fr.renaudboulard.android.customendpoint.model.Repo;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by renaud on 24/08/15.
 */
public interface WebService {

    @GET("/users/{user}/repos")
    void listRepos(@Path("user") String user, Callback<List<Repo>> callback);
}
