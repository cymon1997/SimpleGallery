package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data;

public class BaseResponse<T> {

    private int status;
    private String message;
    private T payload;

    public BaseResponse(int status, String message, T payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    public BaseResponse(T payload) {
        this.payload = payload;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public T getPayload() {
        return this.payload;
    }
}
