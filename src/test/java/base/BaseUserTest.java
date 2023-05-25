package base;

import actions.UserAction;
import generating.GenerateUserData;
import constants.UserFields;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import resources.*;

public class BaseUserTest extends UserAction {
    private final GenerateUserData generateUserData = new GenerateUserData();
    public UserCard userCard;

    // Creation User part
    public Response requestCreateNewUser(){
        generateNewUserCard();
        return postRequestCreateUser(userCard);
    }

    @Step("Send request > create new user")
    public Response requestCreateUser(){
        return postRequestCreateUser(userCard);
    }

    // Login user part
    @Step("Send request > user login")
    public Response requestUserLogin(){
        return postRequestLogIn(userCard);
    }

    // Update user part
    @Step("Send request > update user data")
    public Response updateUserData(String userToken) {
        return patchRequestUpdateUserData(userCard, userToken);
    }

    @Step("Send request > update user data without auth token")
    public Response updateUserDataNoAuth(){
        return patchRequestUpdateUserData(userCard);
    }

    // Removing test-user part
    @Step("Send request > remove created test user by token")
    public void deleteTestUser(String userToken) {
        deleteRequestRemoveUserByToken(userToken);
    }

    // Creation UserCard
    @Step("Generate new UserCard with Random data")
    public void generateNewUserCard()
    {
        generateUserData.generateEmailPassName();
        userCard = new UserCard(
                generateUserData.getUserEmail(),
                generateUserData.getUserPassword(),
                generateUserData.getUserName());
    }

    @Step("Update UserCard by random value")
    public String changeUserCardValueToRandom(String updatedField)
    {
        generateUserData.generateEmailPassName();
        String fieldValue = "";
        switch(updatedField) {
            case UserFields.EMAIL: fieldValue = generateUserData.getUserEmail();
                break;
            case UserFields.PASSWORD: fieldValue = generateUserData.getUserPassword();
                break;
            case UserFields.NAME: fieldValue = generateUserData.getUserName();
                break;
        }
        changeUserCardValue(updatedField, fieldValue);
        return fieldValue;
    }

    @Step("Update UserCard by custom value")
    public void changeUserCardValue(String userField, String fieldValue)
    {
        switch(userField) {
            case UserFields.EMAIL: userCard.setEmail(fieldValue);
                break;
            case UserFields.PASSWORD: userCard.setPassword(fieldValue);
                break;
            case UserFields.NAME: userCard.setName(fieldValue);
                break;
        }
    }
}
