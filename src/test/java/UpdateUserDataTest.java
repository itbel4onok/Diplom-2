import base.BaseTest;
import constants.ErrorMessage;
import constants.UserFields;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

@Feature("Update user data - PATCH /api/auth/user")
@DisplayName("Update user data")
public class UpdateUserDataTest extends BaseTest {
    @Test
    @DisplayName("Update user data > update password")
    @Description("Send correct PATCH request to /api/auth/login")
    public void updateUserPassTest() {
        requestCreateCorrectUser();
        baseUserTest.changeUserCardValueToRandom(UserFields.PASSWORD);
        Response response = baseUserTest.updateUserData(getUserToken());
        response.then()
                .assertThat().body("success", equalTo(true))
                .and().statusCode(SC_OK);
    }

    @Test
    @DisplayName("Update user data > update without authorization token")
    @Description("Impossible to update user data without authorization")
    public void updateUserDataNoAuthTest() {
        requestCreateCorrectUser();
        Response response = baseUserTest.updateUserDataNoAuth();
        response.then()
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo(ErrorMessage.AUTH_REQUIRED))
                .and().statusCode(SC_UNAUTHORIZED);
    }

    @Test
    @DisplayName("Update user data > update email to existed")
    @Description("Impossible to update user data to existed email")
    public void updateUserDataExistEmailTest() {
        requestCreateCorrectUser();
        String firstCreatedEmail = baseUserTest.userCard.getEmail();
        requestCreateCorrectUser();
        baseUserTest.changeUserCardValue(UserFields.EMAIL, firstCreatedEmail);
        Response response = baseUserTest.updateUserData(getUserToken());
        response.then()
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo(ErrorMessage.EXISTED_EMAIL))
                .and().statusCode(SC_FORBIDDEN);
    }

    @After
    public void cleanTestData() {
        deleteTestUser();
    }
}
