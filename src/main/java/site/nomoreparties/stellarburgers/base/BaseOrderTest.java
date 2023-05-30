package site.nomoreparties.stellarburgers.base;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import site.nomoreparties.stellarburgers.actions.OrderAction;
import site.nomoreparties.stellarburgers.constants.IngredientsIds;
import site.nomoreparties.stellarburgers.resources.OrderCard;

public class BaseOrderTest extends OrderAction {
    // Creation order part
    @Step("Send request > create new order")
    public Response requestCreateCorrectOrder(String userToken) {
        return postRequestCreateOrder(new OrderCard(IngredientsIds.INGREDIENTS_CORRECT), userToken);
    }

    @Step("Send request > create new order")
    public Response requestCreateCorrectOrder() {
        return postRequestCreateOrder(new OrderCard(IngredientsIds.INGREDIENTS_CORRECT));
    }

    @Step("Send request > create new order")
    public Response requestCreateOrder(String[] ingredients, String userToken) {
        return postRequestCreateOrder(new OrderCard(ingredients), userToken);
    }

    @Step("Send request > create new order")
    public Response requestCreateOrder(String[] ingredients) {
        return postRequestCreateOrder(new OrderCard(ingredients));
    }

    @Step("Send request > get orders")
    public Response requestGetOrders() {
        return getRequestGetOrders();
    }

    @Step("Send request > get orders")
    public Response requestGetOrders(String userToken) {
        return getRequestGetOrders(userToken);
    }
}
