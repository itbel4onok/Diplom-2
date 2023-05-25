package resources;

public class UserTokenCard {
    private String userToken;

    public UserTokenCard(String userToken) {
        this.userToken = userToken;
    }

    public UserTokenCard() {
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
