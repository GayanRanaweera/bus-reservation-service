package enactor.brs.servlet;

import enactor.brs.builder.RequestBuilder;
import enactor.brs.constant.Constants;
import enactor.brs.constant.ErrorMessage;
import enactor.brs.model.request.AvailabilityRequest;
import enactor.brs.model.response.AvailabilityResponse;
import enactor.brs.service.AvailabilityService;
import enactor.brs.service.ValidationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AvailabilityServlet extends BaseServlet {

    private final AvailabilityService availabilityService = new AvailabilityService();
    private final ValidationService validationService = new ValidationService();
    private static final Logger logger = LogManager.getLogger(AvailabilityServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            // Read query string
            logger.info("Incoming availability request: {}", request.getQueryString());

            // Convert JSON -> Object
            AvailabilityRequest availabilityRequest = RequestBuilder.buildAvailabilityRequest(request);
            logger.info("availabilityRequest : {}", availabilityRequest);

            // Validate
            validationService.validateAvailability(availabilityRequest);

            // Process
            AvailabilityResponse availabilityResponse = availabilityService.checkAvailability(availabilityRequest);

            // Respond
            writeResponse(response, availabilityResponse, HttpServletResponse.SC_OK);

            logger.info("availabilityResponse: {}", availabilityResponse);

        } catch (IllegalArgumentException e) {

            logger.error("Error in AvailabilityServlet: {}", e);

            writeResponse(response,
                    errorResponse(e.getMessage()),
                    HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {

            logger.error("Error in AvailabilityServlet: {}", e);

            writeResponse(response,
                    errorResponse(ErrorMessage.INTERNAL_SERVER_ERROR.getMessage()),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private AvailabilityResponse errorResponse(String message) {
        AvailabilityResponse availabilityResponse = new AvailabilityResponse();
        availabilityResponse.setStatus(Constants.STATUS_FAIL);
        availabilityResponse.setReason(message);
        return availabilityResponse;
    }
}