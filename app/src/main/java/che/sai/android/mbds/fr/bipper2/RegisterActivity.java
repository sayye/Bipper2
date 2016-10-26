package che.sai.android.mbds.fr.bipper2;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person();
                EditText nom = (EditText)findViewById(R.id.editTextNom);
                person.setNom(nom.getText().toString());
                EditText prenom = (EditText)findViewById(R.id.editTextPrenom);
                person.setPrenom(prenom.getText().toString());
                EditText telephone = (EditText)findViewById(R.id.editTextTel);
                person.setTelephone(Integer.parseInt(telephone.getText().toString()));
                EditText email = (EditText)findViewById(R.id.editTextMail);
                person.setEmail(email.getText().toString());
                EditText pwd = (EditText)findViewById(R.id.editTextPwd);
                person.setPassword(pwd.getText().toString());
                person.setCreatedBy("che.sai");

                // radio a Traiter
                person.setSexe("Femme");
                //lancer la requête
                new RegisterTask().execute(person);
            }
        });

    }




class RegisterTask extends AsyncTask<Person,Void,Person> {

    @Override
    protected Person doInBackground(Person... people) {
        try {
            String url = "http://95.142.161.35:1337/person/";
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);

            // add header

            post.setHeader("Content-Type", "application/json");

            Person person = people[0];
            JSONObject obj = new JSONObject();
            obj.put("prenom", person.getPrenom());
            obj.put("nom", person.getNom());
            obj.put("sexe", person.getSexe());
            obj.put("telephone", person.getTelephone());
            obj.put("email", person.getEmail());
            obj.put("createdby", person.getCreatedBy());
            obj.put("password", person.getPassword());
            // SM ORI: obj.toJSONString() obj.toString()
            StringEntity entity = new StringEntity(obj.toString());

            post.setEntity(entity);

            HttpResponse response = client.execute(post);


            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + post.getEntity());
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            JSONObject json = new JSONObject(result.toString());
            String updatedAt= (String) json.get("updatedAt");
            String createdAt= (String) json.get("createdAt");
            String id= (String) json.get("id");
            person.setConnected(true);
            person.setUpdateAt(updatedAt);
            person.setCreatedAt(createdAt);
            person.setId(id);

            System.out.println(result.toString());
            return person;

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
    protected void onPostExecute(Person person) {
        super.onPostExecute(person);
        showProgressDialog(false);
        Toast.makeText(RegisterActivity.this, R.string.inscription_ok, Toast.LENGTH_LONG).show();
        //Enlever le loading
        //Traiter la person
        if (person != null) {
        } else {
        }
        //Renvoyer vers le login
        //Fermer l'activité Enregistrer
    }


    ProgressDialog progressDialog;

    public void showProgressDialog(boolean isVisible) {
        if (isVisible) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setMessage(RegisterActivity.this.getResources().getString(R.string.please_wait));
                progressDialog.setCancelable(false);
                progressDialog.setIndeterminate(true);
                progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        progressDialog = null;
                    }
                });
                progressDialog.show();
            }
        } else {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }
}



}