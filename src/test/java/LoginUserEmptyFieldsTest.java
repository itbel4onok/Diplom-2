import base.BaseTest;
import constants.ErrorMessage;
import constants.UserFields;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.equalTo;

@Feature("User login - POST /api/auth/login")
@DisplayName("User login")
@RunWith(Parameterized.class)
public class LoginUserEmptyFieldsTest extends BaseTest {
    private final String userField;
    private final String fieldValue;
    public LoginUserEmptyFieldsTest(String userField, String fieldValue) {
        this.userField = userField;
        this.fieldValue = fieldValue;
    }

    @Parameterized.Parameters(name = "field: {0}, value: {1}")
    public static Object[][] getValueOfField() {
        return new Object[][]{
                { UserFields.PASSWORD, UserFields.RANDOM_PASSWORD },
                { UserFields.PASSWORD, UserFields.NULL_VALUE },
                { UserFields.PASSWORD, UserFields.EMPTY_VALUE },
                { UserFields.EMAIL, UserFields.NULL_VALUE },
                { UserFields.EMAIL, UserFields.EMPTY_VALUE },
        };
    }

    @Test
    public void loginUserWithIncorrectDataTest() {
        requestCreateCorrectUser();
        baseUserTest.changeUserCardValue(userField, fieldValue);
        Response response = baseUserTest.requestUserLogin();
        response.then()
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_LOG_IN))
                .and().statusCode(SC_UNAUTHORIZED);
    }

    @After
    public void cleanTestData() {
        deleteTestUser();
    }
}
