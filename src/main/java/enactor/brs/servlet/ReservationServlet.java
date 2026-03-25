package enactor.brs.servlet;

import enactor.brs.builder.RequestBuilder;
import enactor.brs.constant.Constants;
import enactor.brs.constant.ErrorMessage;
import enactor.brs.model.request.ReservationRequest;
import enactor.brs.model.response.ReservationResponse;
import enactor.brs.service.ReservationService;
import enactor.brs.service.ValidationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReservationServlet extends BaseServlet {

    private ReservationService reservationService = new ReservationService();
    private ValidationService validationService = new ValidationService();
    private static final Logger logger = LogManager.getLogger(ReservationServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Read payload once
            String payLoad = getPayload(request);
            logger.info("Incoming reservation request: {}", payLoad);

            // Convert JSON -> Object
            ReservationRequest reservationRequest = RequestBuilder.buildReservationRequest(payLoad);
            logger.info("reservationRequest : {}", reservationRequest);

            // Validate
            validationService.validateReservation(reservationRequest);

            // Process
            ReservationResponse reservationResponse = reservationService.reserveSeats(reservationRequest);

            // Respond
            writeResponse(response, reservationResponse, HttpServletResponse.SC_OK);

            logger.info("reservationResponse: {}", reservationResponse);

        } catch (IllegalArgumentException e) {

            logger.error("Error in ReservationService: {}", e);

            writeResponse(response,
                    errorResponse(e.getMessage()),
                    HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {

            logger.error("Error in ReservationService: {}", e);

            writeResponse(response,
                    errorResponse(ErrorMessage.INTERNAL_SERVER_ERROR.getMessage()),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private ReservationResponse errorResponse(String message) {
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setStatus(Constants.STATUS_FAIL);
        reservationResponse.setReason(message);
        return reservationResponse;
    }
}
