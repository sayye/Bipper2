package che.sai.android.mbds.fr.bipper2;

import java.io.Serializable;

/**
 * Created by sai on 10/26/16.
 */
// Serializable  ???
public class Login  implements Serializable {
    private Person person=null;
    private String email=null;
    private String password=null;
    private String message=null;
    private Boolean success=false;


    public Login() {
    }

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
