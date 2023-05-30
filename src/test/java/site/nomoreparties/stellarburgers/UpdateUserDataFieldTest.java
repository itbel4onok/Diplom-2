package site.nomoreparties.stellarburgers;

import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import site.nomoreparties.stellarburgers.base.BaseTest;
import site.nomoreparties.stellarburgers.constants.UserFields;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

@Feature("Update user data - PATCH /api/auth/user")
@DisplayName("Update user data")
@RunWith(Parameterized.class)
public class UpdateUserDataFieldTest extends BaseTest {
    private final String userField;
    private final String body;

    public UpdateUserDataFieldTest(String userField, String body) {
        this.userField = userField;
        this.body = body;
    }

    @Parameterized.Parameters(name = "Update user field: {0}")
    public static Object[][] getUpdatedValue() {
        return new Object[][]{
                {UserFields.EMAIL, "user.email"},
                {UserFields.NAME, "user.name"},
        };
    }

    @Test
    public void updateUserDataTest() {
        requestCreateCorrectUser();
        String updatedValue = baseUserTest.changeUserCardValueToRandom(userField);
        Response response = baseUserTest.updateUserData(getUserToken());
        response.then()
                .assertThat().statusCode(SC_OK)
                .assertThat().body("success", equalTo(true))
                .assertThat().body(body, equalTo(updatedValue));
    }

    @After
    public void cleanTestData() {
        deleteTestUser();
    }
}
