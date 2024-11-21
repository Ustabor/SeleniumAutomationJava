package entities;

public class RequestResult {
    public String requestId;
    public User guest;

    public RequestResult(String requestId, User guest) {
        this.requestId = requestId;
        this.guest = guest;
    }
}
