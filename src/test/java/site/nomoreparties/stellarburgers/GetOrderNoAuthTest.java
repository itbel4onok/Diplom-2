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

@Feature("Get orders - GET /api/orders")
@DisplayName("Get orders")
public class GetOrderNoAuthTest extends BaseTest {
    @Test
    @DisplayName("Get orders > without auth")
    @Description("Impossible view orders without auth")
    public void getOrderNoAuthTest() {
        Response response = baseOrderTest.requestGetOrders();
        response.then()
                .assertThat().statusCode(SC_UNAUTHORIZED)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo(ErrorMessage.AUTH_REQUIRED));
    }
}
