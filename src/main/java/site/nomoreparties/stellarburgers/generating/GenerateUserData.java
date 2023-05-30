package site.nomoreparties.stellarburgers.generating;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateUserData {
    private String userEmail;
    private String userPassword;
    private String userName;

    public String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public void generateEmailPassName() {
        userEmail = (generateRandomString() + "@yandex.ru").toLowerCase();
        userPassword = generateRandomString();
        userName = generateRandomString();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }
}
