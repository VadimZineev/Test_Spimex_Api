import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MainServlet", value = "/MainServlet")
public class MainServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.process(request, response);

    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            int code = Integer.parseInt(request.getParameter("code"));
            out.println("<h1>Number of active records:</h1>");
            out.println("<h1>" + Service.getActiveRecords(code) + "</h1>");
        } catch (Exception e) {
            out.println("<h1>Invalid Format! Use numbers only!</h1>");
        }
        out.close();
    }
}
