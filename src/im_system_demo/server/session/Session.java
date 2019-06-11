package im_system_demo.server.session;

/**
 * @author xiong
 * @date 2019-06-05  09:55
 */
public class Session {

    private String username;
    private String currentGroupNickename;

    public String getCurrentGroupNickename() {
        return currentGroupNickename;
    }

    public void setCurrentGroupNickename(String currentGroupNickename) {
        this.currentGroupNickename = currentGroupNickename;
    }

    public Session(){}
    public Session(String username){
        this.username = username;
    }
    public Session(String username, String currentGroupNickename){
        this.username = username;
        this.currentGroupNickename = currentGroupNickename;
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
