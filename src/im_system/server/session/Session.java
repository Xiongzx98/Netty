package im_system.server.session;

/**
 * @author xiong
 * @date 2019-06-05  09:55
 */
public class Session {

    private String username;

    public Session(){}
    public Session(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username;
    }
}
