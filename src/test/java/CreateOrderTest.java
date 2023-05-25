import base.BaseTest;
import constants.ErrorMessage;
import constants.IngredientsIds;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Feature("Create order - POST /api/orders")
@DisplayName("Create order")
public class CreateOrderTest extends BaseTest {
    @Test
    @DisplayName("Create order > correct ids > with auth")
    @Description("Send correct POST request to /api/orders")
    public void createOrderWithAuthTest() {
        requestCreateCorrectUser();
        Response response = baseOrderTest.requestCreateCorrectOrder(getUserToken());
        response.then()
                .assertThat().body("success", equalTo(true))
                .assertThat().body("order.number", notNullValue())
                .and().statusCode(SC_OK);
    }

    @Test
    @DisplayName("Create order > empty ids > with auth")
    @Description("Send request with empty ingredients list")
    public void createEmptyOrderWithAuthTest() {
        requestCreateCorrectUser();
        Response response = baseOrderTest.requestCreateOrder(
                IngredientsIds.INGREDIENTS_EMPTY, getUserToken());
        response.then()
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo(ErrorMessage.NO_INGREDIENTS))
                .and().statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Create order > incorrect ids > with auth")
    @Description("Send request with incorrect ingredients list")
    public void createIncorrectOrderWithAuthTest() {
        requestCreateCorrectUser();
        Response response = baseOrderTest.requestCreateOrder(
                IngredientsIds.INGREDIENTS_RANDOM, getUserToken());
        response.then().assertThat().statusCode(SC_INTERNAL_SERVER_ERROR);
    }

    @After
    public void cleanTestData() {
        deleteTestUser();
    }
}
