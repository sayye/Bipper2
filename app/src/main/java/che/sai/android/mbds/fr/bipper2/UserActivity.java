package che.sai.android.mbds.fr.bipper2;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity implements View.OnClickListener{





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        new UserTask().execute();
        //
       // findViewById(R.id.deleteBtn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //v.getTag()
        if (v.getId()==R.id.deleteBtn){
            new UserTaskDelete().execute((String) v.getTag());
        }

    }

    class UserTask extends AsyncTask< Void,Void,List<Person> > {

        @Override
        protected List<Person> doInBackground(Void... params) {
            try {
                String url = "http://95.142.161.35:1337/person/";
                HttpClient client = new DefaultHttpClient();
                //HttpPost post = new HttpPost(url);
                HttpGet httpGet = new HttpGet(url);
                httpGet.setHeader("Content-Type", "application/json");

                HttpResponse response = client.execute(httpGet);

                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

                ArrayList<Person> personList = new ArrayList<Person>();
                JSONArray jsonArray = new JSONArray(result.toString());
                for(int i=0; i<jsonArray.length();i++){
                    JSONObject json = jsonArray.getJSONObject(i);
                    Person person = new Person();
                    person.setPrenom(!json.has("prenom")?null:json.getString("prenom"));
                    person.setId(!json.has("id")?null:json.getString("id"));
                    person.setCreatedAt(!json.has("createdAt")?null:json.getString("createdAt"));
                    person.setCreatedBy(!json.has("createdby")?null:json.getString("createdby"));
                    person.setUpdateAt(!json.has("updatedAt")?null:json.getString("updatedAt"));
                    person.setPassword(!json.has("password")?null:json.getString("password"));
                    person.setEmail(!json.has("email")?null:json.getString("email"));
                    person.setNom(!json.has("nom")?null:json.getString("nom"));
                    person.setSexe(!json.has("sexe")?null:json.getString("sexe"));
                    if(json.has("telephone")){
                        try{
                            person.setTelephone(json.getInt("telephone"));
                        }
                        catch(Exception e){
                            person.setTelephone(0);
                        }
                    }
                    //person.setTelephone(json.has("telephone")?0:json.getInt("telephone"));
                    person.setConnected(!json.has("connected")?false:json.getBoolean("connected"));

                    personList.add(person);
                }

                return personList;

            } catch (Exception e) {

            }
            return null;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog(true);
        }

        @Override
        protected void onPostExecute(List<Person> personList) {
            super.onPostExecute(personList);
            PersonItemAdapter personItemAdapter = new PersonItemAdapter(UserActivity.this,personList, UserActivity.this);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(personItemAdapter);

        }


        ProgressDialog progressDialog;

        public void showProgressDialog(boolean isVisible) {

        }
    }



    class UserTaskDelete extends AsyncTask< String,Void,Void > {

        @Override
        protected Void doInBackground(String... ids) {
            try {
                //Integer
                String id = ids[0];

                String url = "http://95.142.161.35:1337/person/"+id;
                HttpClient client = new DefaultHttpClient();
                HttpDelete httpDelete = new HttpDelete(url);
                httpDelete.setHeader("Content-Type", "application/json");
                HttpResponse response = client.execute(httpDelete);
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            } catch (Exception e) {

            }
            return null;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog(true);
        }

        @Override
        protected void onPostExecute(Void param) {
            super.onPostExecute(param);
            //PersonItemAdapter personItemAdapter = new PersonItemAdapter(UserActivity.this,personList);
            //personItemAdapter.person.remove(pos);
            //ListView listView = (ListView) findViewById(R.id.listView);

            //listView.setAdapter(personItemAdapter);
            new UserTask().execute();

        }


        ProgressDialog progressDialog;

        public void showProgressDialog(boolean isVisible) {

        }
    }


}
