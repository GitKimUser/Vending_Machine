package vending;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<String, Item> items = new HashMap<>();

    public void add(Item item){
        items.put(item.getName(), item);
    }
    public Item findByName(String name){
        if(!items.containsKey(name)){
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다.");
        }
        return items.get(name);
    }
}
