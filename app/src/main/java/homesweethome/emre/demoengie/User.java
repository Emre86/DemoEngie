package homesweethome.emre.demoengie;

/**
 * Created by emre on 07/05/17.
 */
public class User {

    private ValidString name;
    private ValidString email;
    private int id;

    public User(ValidString name, ValidString email){
        this.name = name;
        this.email = email;
        this.id = -1;
    }

    public User(ValidString name, ValidString email, int id){
        this.name = name;
        this.email = email;
        this.id = id;
    }


    public ValidString getName(){
        return this.name;
    }


    public String getNameString(){
        return this.name.getString();
    }

    public String getEmailString(){
        return this.email.getString();
    }


    public int getId(){
        return this.id;
    }


    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("{\"user\":{\"email\":\"");
        stringBuilder.append(this.email.getString()).append("\",\"name\":\"").append(this.name.getString()).append("\"}}");
        return stringBuilder.toString();
    }


}
