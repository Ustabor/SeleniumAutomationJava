package entities;

public class RequestResult {
    public String requestInnerId;
    public String requestOuterId;
    public User guest;

    public RequestResult(String requestInnerId, String requestOuterId, User guest) {
        this.requestInnerId = requestInnerId;
        this.requestOuterId = requestOuterId;
        this.guest = guest;
    }
}
