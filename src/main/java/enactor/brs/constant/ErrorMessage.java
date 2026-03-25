package enactor.brs.constant;

public enum ErrorMessage {

    INVALID_ORIGIN("Invalid origin"),
    ORIGIN_REQUIRED("Origin is required"),
    INVALID_DESTINATION("Invalid destination"),
    DESTINATION_REQUIRED("Destination is required"),
    SAME_LOCATION("Origin and destination cannot be same"),
    PASSENGERS_REQUIRED("Passengers is required"),
    PASSENGERS_MUST_BE_NUMBER("Passengers must be a number"),
    INVALID_PASSENGERS("Passengers must be greater than 0"),
    CONFIRM_PRICE_REQUIRED("Confirm price is required"),
    CONFIRM_PRICE_MUST_BE_NUMBER("Confirm price must be a number"),
    INVALID_CONFIRM_PRICE("Confirm price must be greater than 0"),
    PRICE_MISMATCH("Price mismatch"),
    NOT_ENOUGH_SEATS("Not enough seats available"),
    INVALID_JSON_FORMAT_OR_DATA_TYPE("Invalid JSON format or data type"),
    INVALID_REQUEST_BODY("Invalid request body"),
    EMPTY_REQUEST_BODY("Request body is empty"),
    INTERNAL_SERVER_ERROR("Internal Server Error");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}