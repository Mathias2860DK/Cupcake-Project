package business.entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int toppingId;
    private int bottomId;
    List<Orderline> orderlines = new ArrayList<>();
}
