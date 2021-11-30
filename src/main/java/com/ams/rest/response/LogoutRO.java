package com.ams.rest.response;

/**
 * Logout REST response object.
 *
 * @author Alexey Mironov
 */
public class LogoutRO {

    private String message;

    private boolean success;

    public LogoutRO(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public LogoutRO(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
