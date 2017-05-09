package homesweethome.emre.demoengie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;



public class FormulaireActivity extends AppCompatActivity {

    final String TAG = "FormulaireActivity";

    User user;

    EditText editTextName ;
    EditText editTextMail ;
    Button button;

    Network network;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);
        editTextName = (EditText) findViewById(R.id.edittextFormulaireName);
        editTextMail = (EditText) findViewById(R.id.edittextFormulaireMail);
        button = (Button) findViewById(R.id.buttonFormulaire);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ValidString name = new ValidString(editTextName.getText().toString());
                ValidString email = new ValidString(editTextMail.getText().toString());
                if(email.isValidEmail() && name.isValidName()){
                    user = new User(name,email);
                    checkUser();
                }else {
                    Toast.makeText(getBaseContext(),"Mail non valide", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }





    private void checkUser() {
        getObservable()
                .doAfterNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String responseNetwork) throws Exception {
                        if (responseNetwork.startsWith("201")){

                            JSONObject jsonObject = new JSONObject(responseNetwork.substring(4));
                            JSONObject jsonObjectUser = (JSONObject) jsonObject.get("user");

                            ValidString name = new ValidString((String)jsonObjectUser.get("name"));
                            ValidString email = new ValidString((String)jsonObjectUser.get("email"));
                            int id = (int) jsonObject.get("id");

                            User user = new User(name,email,id);
                            UserState userState = new UserState();
                            userState.writeUserState(user,getApplicationContext());

                            Intent intentUserEngie = new Intent(getApplicationContext(),UserEngie.class);
                            startActivity(intentUserEngie);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Compte Non créer",Toast.LENGTH_SHORT).show();
                        }


                    }
                })
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<String> getObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                network = new Network();
                String response = network.postData(user);
                emitter.onNext(response);
                emitter.onComplete();
            }
        });
    }


    private Observer<String> getObserver() {
        return new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(String value) {
                Log.i(TAG, " onNext ");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, " onComplete");
            }
        };
    }

}
