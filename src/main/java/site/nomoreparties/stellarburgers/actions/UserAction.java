package site.nomoreparties.stellarburgers.actions;

import io.restassured.response.Response;
import site.nomoreparties.stellarburgers.base.BaseApi;
import site.nomoreparties.stellarburgers.constants.PathApi;

import static io.restassured.RestAssured.given;

public class UserAction extends BaseApi {
    public UserAction() {
    }

    protected Response postRequestCreateUser(Object obj) {
        return given(RequestSpecification())
                .body(obj)
                .post(PathApi.CREATE_USER);
    }

    protected Response postRequestLogIn(Object obj) {
        return given(RequestSpecification())
                .body(obj)
                .post(PathApi.LOGIN_USER);
    }

    protected Response patchRequestUpdateUserData(Object obj, String userToken) {
        return given(RequestSpecification())
                .auth().oauth2(userToken)
                .body(obj)
                .patch(PathApi.UPDATE_USER_DATA);
    }

    protected Response patchRequestUpdateUserData(Object obj) {
        return given(RequestSpecification())
                .body(obj)
                .patch(PathApi.UPDATE_USER_DATA);
    }

    protected void deleteRequestRemoveUserByToken(String userToken) {
        given(RequestSpecification())
                .auth().oauth2(userToken)
                .delete(PathApi.REMOVE_USER);
    }
}
