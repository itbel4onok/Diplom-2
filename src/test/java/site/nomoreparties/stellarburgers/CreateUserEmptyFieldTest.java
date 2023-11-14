package site.nomoreparties.stellarburgers;

import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import site.nomoreparties.stellarburgers.base.BaseTest;
import site.nomoreparties.stellarburgers.constants.ErrorMessage;
import site.nomoreparties.stellarburgers.constants.UserFields;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.hamcrest.Matchers.equalTo;


@Feature("Create user - POST /api/auth/register")
@DisplayName("Create user")
@RunWith(Parameterized.class)
public class CreateUserEmptyFieldTest extends BaseTest {
    private final String userField;
    private final String fieldValue;

    public CreateUserEmptyFieldTest(String userField, String fieldValue) {
        this.userField = userField;
        this.fieldValue = fieldValue;
    }

    @Parameterized.Parameters(name = "field: {0}, value: {1}")
    public static Object[][] getValueOfField() {
        return new Object[][]{
                {UserFields.EMAIL, UserFields.NULL_VALUE},
                {UserFields.EMAIL, UserFields.EMPTY_VALUE},
                {UserFields.PASSWORD, UserFields.NULL_VALUE},
                {UserFields.PASSWORD, UserFields.EMPTY_VALUE},
                {UserFields.NAME, UserFields.NULL_VALUE},
                {UserFields.NAME, UserFields.EMPTY_VALUE},
        };
    }

    @Test
    public void CreateUserEmptyValueTest() {
        baseUserTest.generateNewUserCard();
        baseUserTest.changeUserCardValue(userField, fieldValue);
        Response response = baseUserTest.requestCreateUser();
        response.then()
                .assertThat().statusCode(SC_FORBIDDEN)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_CREATE));
    }
}
