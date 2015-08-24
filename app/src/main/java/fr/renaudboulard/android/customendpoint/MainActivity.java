package fr.renaudboulard.android.customendpoint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import fr.renaudboulard.android.customendpoint.model.Repo;
import fr.renaudboulard.android.customendpoint.network.CustomEndPoint;
import fr.renaudboulard.android.customendpoint.network.WebService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity implements URLInterface,View.OnClickListener{

    /**
     * Constantes
     */
    private final static String URL_1 = "https://api.github.com";
    private final static String URL_2 = "https://api.twitter.com";
    private final static String REPO ="renaudboulard";

    /**
     * Views
     */
    private TextView text;
    private TextView textResponse;
    private Button button;
    private Switch switchButton;

    /**
     * Service
     */
    private WebService webService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView)findViewById(R.id.text);
        textResponse = (TextView)findViewById(R.id.textResponse);
        button = (Button)findViewById(R.id.button);
        switchButton = (Switch)findViewById(R.id.switchButton);
        button.setOnClickListener(this);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(new CustomEndPoint(this))
                .build();

        webService = restAdapter.create(WebService.class);
    }

    /**
     * Return custom url at the runtime
     * For every call to webService the getCustomUrl method will return the right url
     * @return url
     */
    @Override
    public String getCustomUrl() {
        if(switchButton.isChecked()){
            return URL_1;
        }else{
            return URL_2;
        }
    }

    @Override
    public void onClick(View view) {
        Callback callback= new Callback() {
            @Override
            public void success(Object o, Response response) {
                Log.i("WebService","Success");
                StringBuffer stringBuffer = new StringBuffer();
                List<Repo> list =(List<Repo>)o;
                for(Repo repo:list){
                    stringBuffer.append(repo.name);
                    stringBuffer.append("\n");
                }

                textResponse.setText(stringBuffer.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("WebService","failure");
                textResponse.setText(error.toString());
            }
        };

        webService.listRepos(REPO, callback);
    }
}
