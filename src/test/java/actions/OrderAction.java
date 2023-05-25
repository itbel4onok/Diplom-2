package actions;

import base.BaseApi;
import constants.PathApi;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderAction extends BaseApi {
    public OrderAction(){
    }

    protected Response postRequestCreateOrder(Object obj, String userToken){
        return given(RequestSpecification())
                .auth().oauth2(userToken)
                .body(obj)
                .post(PathApi.ORDER_ACTIONS);
    }

    protected Response postRequestCreateOrder(Object obj){
        return given(RequestSpecification())
                .body(obj)
                .post(PathApi.ORDER_ACTIONS);
    }

    protected Response getRequestGetOrders(){
        return given(RequestSpecification())
                .get(PathApi.ORDER_ACTIONS);
    }

    protected Response getRequestGetOrders(String userToken){
        return given(RequestSpecification())
                .auth().oauth2(userToken)
                .get(PathApi.ORDER_ACTIONS);
    }
}
