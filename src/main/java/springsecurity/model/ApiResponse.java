package springsecurity.model;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private String message;
    private HttpStatus status;
    private T payload;

    // Constructor
    public ApiResponse(String message, HttpStatus status, T payload) {
        this.message = message;
        this.status = status;
        this.payload = payload;
    }

    // Getters and setters (if not using Lombok)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}

