import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

@Feature("User login - POST /api/auth/login")
@DisplayName("User login")
public class LoginUserTest extends BaseTest {
    @Test
    @DisplayName("User login > happy path")
    @Description("Send correct POST request to /api/auth/login")
    public void loginUserHappyPathTest() {
        requestCreateCorrectUser();
        Response response = baseUserTest.requestUserLogin();
        response.then().assertThat().body("success", equalTo(true))
                .and().statusCode(SC_OK);
    }

    @After
    public void cleanTestData() {
        deleteTestUser();
    }
}
