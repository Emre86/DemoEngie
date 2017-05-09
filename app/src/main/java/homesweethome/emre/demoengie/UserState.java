package homesweethome.emre.demoengie;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by emre on 08/05/17.
 */
public class UserState {

    private SharedPreferences sharedPreferences ;
    private String FilePreferences= "USERSTATE_PREFERENCES";

    public boolean writeUserState(User user,Context context){
        sharedPreferences = context.getSharedPreferences(FilePreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String userName = user.getNameString();
        String userEmail = user.getEmailString();
        int userId = user.getId();
        editor.putString("Name",userName);
        editor.putString("Email",userEmail);
        editor.putInt("ID",userId);
        return editor.commit();
    }

    public User readUserState(Context context){
        sharedPreferences = context.getSharedPreferences(FilePreferences,Context.MODE_PRIVATE);
        if (!sharedPreferences.contains("Name")){
            return null;
        }
        String name = sharedPreferences.getString("Name","DEFAULT");
        String email = sharedPreferences.getString("Email","DEFAULT");
        int id = sharedPreferences.getInt("ID",-1);
        ValidString validName = new ValidString(name);
        ValidString validEmail = new ValidString(email);
        return new User(validName,validEmail,id);
    }


    public void clearUserState(Context context){
        sharedPreferences = context.getSharedPreferences(FilePreferences, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

}
