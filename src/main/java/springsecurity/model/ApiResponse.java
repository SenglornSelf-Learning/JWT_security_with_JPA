package springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    // Getters and setters (if not using Lombok)
    private String message;

    private HttpStatus status;
    private T payload;

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

    public ApiResponse(String message, HttpStatus status, T payload) {
        this.message = message;
        this.status = status;
        this.payload = payload;
    }

    public ApiResponse() {
    }
}

