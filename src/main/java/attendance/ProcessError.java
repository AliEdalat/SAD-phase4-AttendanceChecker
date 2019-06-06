package attendance;

public class ProcessError extends Exception {
    private String message;
    public ProcessError(String message){
        this.message = message;
    }

    public String toString() {
        return this.message;
    }
}
