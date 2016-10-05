package leaktest.domain;

public class Part1 {
    private final String payload;

    public Part1(String payload) {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }
}
