package enactor.brs.servlet;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {

    protected final Gson gson = new Gson();

    protected void writeResponse(HttpServletResponse response, Object obj, int httpStatus) throws IOException {
        response.setContentType("application/json");
        response.setStatus(httpStatus);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(obj));
    }
}