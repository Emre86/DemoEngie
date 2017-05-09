package homesweethome.emre.demoengie;

import java.util.regex.Pattern;

/**
 * Created by emre on 07/05/17.
 */
public class ValidString {

    private String string;

    public ValidString(String string){
        this.string = string;
    }

    public String getString(){
        return this.string;
    }

    public boolean isValidEmail(){
        return Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", this.string);
    }

    public boolean isValidName(){
        return true;
    }


}
