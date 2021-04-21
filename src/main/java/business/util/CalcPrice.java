package business.util;

import business.entities.Bottom;
import business.entities.Topping;

import java.util.List;

public class CalcPrice {

    public static double calcOrderLinePrice(int bottomId, int toppingId, int quantity, List<Bottom> bottomList, List<Topping> toppingList) {
        double totalPrice = 0;
        double bottomPrice = 0;
        double toppingPrice = 0;
        for (Bottom bottom : bottomList) {
            if (bottomId == bottom.getBottomId()){
                bottomPrice = bottom.getPrice();
            }
        }
        for (Topping topping : toppingList) {
            if (toppingId == topping.getToppingId()){
                toppingPrice = topping.getPrice();
            }
        }
        totalPrice = (bottomPrice + toppingPrice) * quantity;

        return totalPrice;
    }
}
