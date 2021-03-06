package che.sai.android.mbds.fr.bipper2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recuperer les boutons
      // Button signin = (Button)findViewById(R.id.signin);
       // Button register = (Button)findViewById(R.id.register);

        //evenements click des bontons
        findViewById(R.id.signin).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);

    }
    // Intent
    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.signin:
                intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.register:
                intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
