package homesweethome.emre.demoengie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomePageActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        UserState userState = new UserState();
        User user  = userState.readUserState(getBaseContext());

        if (user != null){
            Intent intentUserEngie = new Intent(getApplicationContext(),UserEngie.class);
            startActivity(intentUserEngie);
        }else {

            final Button buttonInscription = (Button) findViewById(R.id.buttonInscription);
            buttonInscription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentFormulaire = new Intent(getApplicationContext(), FormulaireActivity.class);
                    startActivity(intentFormulaire);
                }
            });
        }
    }




}
