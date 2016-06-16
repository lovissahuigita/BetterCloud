/**
 * Created by Lovissa Winyoto
 */
public class Log {

    private String email, id, message;
    private boolean successful;

    public Log(String email, String id, String message, boolean successful) {
        this.email = email;
        this.id = id;
        this.message = message;
        this.successful = successful;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    @Override
    public String toString() {
        return "Log{" +
                "email='" + email + '\'' +
                ", id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
