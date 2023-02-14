package dev.srebootcamp.client;

public class GatewayException extends RuntimeException {
    private String url;

    public GatewayException(String url, Throwable cause) {
        super("Error connecting to " + url+ " " + cause.getMessage(), cause);
        this.url = url;
    }
}
