package core.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//loadOnStartup : 우선순위래.
@WebServlet(name = "dispatcher", value="/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final String DEFAULT_REDIRECT_PREFIX ="redirect:";
    RequestMapping requestMapping ;
    @Override
    public void init(){
        requestMapping = new RequestMapping();
        requestMapping.initMapping();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        Controller controller = requestMapping.findController(requestURI);
        try {
            String viewName = controller.execute(req,resp);
            move(viewName, req, resp);
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
    public void move(String viewName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(viewName.startsWith(DEFAULT_REDIRECT_PREFIX)){
            resp.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
            return;
        }
        RequestDispatcher rd = req.getRequestDispatcher(viewName);
        rd.forward(req, resp);
    }
}
