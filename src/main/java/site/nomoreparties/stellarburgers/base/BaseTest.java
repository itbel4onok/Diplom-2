package site.nomoreparties.stellarburgers.base;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import site.nomoreparties.stellarburgers.resources.UserTokenCard;

public class BaseTest {
    public final BaseUserTest baseUserTest = new BaseUserTest();
    public final BaseOrderTest baseOrderTest = new BaseOrderTest();
    private UserTokenCard userTokenCard;

    @Step("Send request > create new user")
    public Response requestCreateCorrectUser() {
        Response response = baseUserTest.requestCreateNewUser();
        saveUserToken(response);
        return response;
    }

    // AccessToken part
    private void saveUserToken(Response response) {
        JsonPath jsonPathEvaluator = response.jsonPath();
        String userToken = jsonPathEvaluator.get("accessToken");
        userTokenCard = new UserTokenCard(userToken.substring(7));
    }

    public String getUserToken() {
        return userTokenCard.getUserToken();
    }

    // Removing test-user part
    @Step("Send request > remove created test user by token")
    public void deleteTestUser() {
        baseUserTest.deleteTestUser(userTokenCard.getUserToken());
    }
}
