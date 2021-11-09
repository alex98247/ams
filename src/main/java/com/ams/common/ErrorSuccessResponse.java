package com.ams.common;

public class ErrorSuccessResponse {
    private final Boolean success;
    private final String message;

    private ErrorSuccessResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public static ErrorSuccessResponse success() {
        return new ErrorSuccessResponse(true, "");
    }

    public static ErrorSuccessResponseBuilder builder() {
        return new ErrorSuccessResponseBuilder();
    }

    public static class ErrorSuccessResponseBuilder {
        private Boolean success;
        private String message;

        public ErrorSuccessResponseBuilder success(Boolean success) {
            this.success = success;
            return this;
        }

        public ErrorSuccessResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorSuccessResponse build() {
            return new ErrorSuccessResponse(this.success, this.message);
        }
    }
}
