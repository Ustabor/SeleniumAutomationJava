package entities;

import java.util.Random;

public class Service {
    private String name = "Autotest Service " ;
    private final String description = "Autotest Service Description";
    private final String url = "autotest";
    private final int price = 1000;

    public Service() {
        var random = new Random().nextInt(999999);
        name += random;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }
}
