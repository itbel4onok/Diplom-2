package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import site.nomoreparties.stellarburgers.base.BaseTest;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Feature("Get orders - GET /api/orders")
@DisplayName("Get orders")
public class GetOrderTest extends BaseTest {
    @Test
    @DisplayName("Get orders > with auth")
    @Description("Send correct GET request to /api/orders")
    public void getOrderWitAuthTest() {
        requestCreateCorrectUser();
        baseOrderTest.requestCreateCorrectOrder(getUserToken());
        Response response = baseOrderTest.requestGetOrders(getUserToken());
        response.then()
                .assertThat().statusCode(SC_OK)
                .assertThat().body("success", equalTo(true))
                .assertThat().body("orders._id", notNullValue());
    }

    @After
    public void cleanTestData() {
        deleteTestUser();
    }
}
