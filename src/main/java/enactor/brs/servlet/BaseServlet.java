package enactor.brs.servlet;

import com.google.gson.Gson;
import enactor.brs.constant.ErrorMessage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public abstract class BaseServlet extends HttpServlet {

    protected final Gson gson = new Gson();

    protected void writeResponse(HttpServletResponse response, Object obj, int httpStatus) throws IOException {
        response.setContentType("application/json");
        response.setStatus(httpStatus);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(obj));
    }

    protected String getPayload(HttpServletRequest request) throws IOException {

        StringBuilder payload = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new java.io.InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))) {

            char[] buffer = new char[4096];
            int bytesRead;

            while ((bytesRead = reader.read(buffer)) != -1) {
                payload.append(buffer, 0, bytesRead);
            }
        }

        return payload.toString();
    }
}