package site.nomoreparties.stellarburgers.resources;

public class OrderCard {
    private String[] ingredients;

    public OrderCard(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public OrderCard() {
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }
}
