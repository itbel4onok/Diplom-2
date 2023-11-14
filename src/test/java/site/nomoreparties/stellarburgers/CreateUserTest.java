package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import site.nomoreparties.stellarburgers.base.BaseTest;
import site.nomoreparties.stellarburgers.constants.ErrorMessage;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

@Feature("Create user - POST /api/auth/register")
@DisplayName("Create user")
public class CreateUserTest extends BaseTest {
    @Test
    @DisplayName("Create user > happy path")
    @Description("Send correct POST request to /api/auth/register")
    public void createUserHappyPathTest() {
        Response response = requestCreateCorrectUser();
        response.then()
                .assertThat().statusCode(SC_OK)
                .assertThat().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Create user > create user twice")
    @Description("Impossible to create the same user twice")
    public void createUserTwiceTest() {
        requestCreateCorrectUser();
        Response response = baseUserTest.requestCreateUser();
        response.then()
                .assertThat().statusCode(SC_FORBIDDEN)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo(ErrorMessage.EXIST_LOGIN));
    }

    @After
    public void cleanTestData() {
        deleteTestUser();
    }
}
