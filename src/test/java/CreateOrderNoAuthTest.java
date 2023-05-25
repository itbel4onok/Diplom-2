import base.BaseTest;
import constants.ErrorMessage;
import constants.IngredientsIds;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Feature("Create order - POST /api/orders")
@DisplayName("Create order")
public class CreateOrderNoAuthTest extends BaseTest {
    @Test
    @DisplayName("Create order > correct ids > without auth")
    @Description("Send correct POST request to /api/orders")
    public void createOrderNoAuthTest() {
        Response response = baseOrderTest.requestCreateCorrectOrder();
        response.then()
                .assertThat().body("success", equalTo(true))
                .assertThat().body("order.number", notNullValue())
                .and().statusCode(SC_OK);
    }

    @Test
    @DisplayName("Create order > empty ids > without auth")
    @Description("Send request with empty ingredients list")
    public void createEmptyOrderNoAuthTest() {
        Response response = baseOrderTest.requestCreateOrder(IngredientsIds.INGREDIENTS_EMPTY);
        response.then()
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo(ErrorMessage.NO_INGREDIENTS))
                .and().statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Create order > incorrect ids > without auth")
    @Description("Send request with incorrect ingredients list")
    public void createIncorrectOrderNoAuthTest() {
        Response response = baseOrderTest.requestCreateOrder(IngredientsIds.INGREDIENTS_RANDOM);
        response.then().assertThat().statusCode(SC_INTERNAL_SERVER_ERROR);
    }
}
