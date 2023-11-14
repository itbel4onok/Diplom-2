package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import site.nomoreparties.stellarburgers.base.BaseTest;
import site.nomoreparties.stellarburgers.constants.ErrorMessage;

import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.equalTo;

@Feature("User login - POST /api/auth/login")
@DisplayName("User login")
public class LoginUserNotCreatedUserTest extends BaseTest {
    @Test
    @DisplayName("User login > login with not existing user data")
    @Description("Send POST request to /api/auth/login with incorrect/non-created user data")
    public void loginUserNotCreatedDataTest() {
        baseUserTest.generateNewUserCard();
        Response response = baseUserTest.requestUserLogin();
        response.then()
                .assertThat().statusCode(SC_UNAUTHORIZED)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_LOG_IN));
    }
}
