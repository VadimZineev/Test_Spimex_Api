import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Vadim Zineev
 */
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
        String areaCode = request.getParameter("code");
        if (areaCode.length() < 2) {
            areaCode += 0;
        }
        System.out.println(areaCode);
        int countActiveRecords = Service.getCountActiveRecords(areaCode);
        if (countActiveRecords != 0) {
            out.println("<h1>Number of active records:</h1>");
            out.println("<h1>" + countActiveRecords + "</h1>");
        } else {
            out.println("<h1>Area not found!</h1>");
        }
        out.close();
    }
}
