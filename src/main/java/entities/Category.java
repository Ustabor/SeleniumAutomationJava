package entities;

import java.util.Random;

public class Category {

    private String name = "Autotest" ;
    private String url = "autotest";
    private String systemId;
    private String promoId;
    private Service service;
    private String promotionAndClickId;
    private String requestInnerId;

    public Category() {
        service = new Service();
        var random = new Random().nextInt(999999);
        name += random;
        url += random;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getPromotionAndClickId() {
        return promotionAndClickId;
    }

    public void setPromotionAndClickId(String promotionAndClickId) {
        this.promotionAndClickId = promotionAndClickId;
    }

    public String getRequestInnerId() {
        return requestInnerId;
    }

    public void setRequestInnerId(String requestInnerId) {
        this.requestInnerId = requestInnerId;
    }
}
