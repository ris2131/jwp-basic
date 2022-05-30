package next.web;

import next.support.context.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/logout")
public class LogoutUserServlet extends HttpServlet {
    public static final Logger log = LoggerFactory.getLogger(LogoutUserServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("logout button");
        //session attribute "user" 해제
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        log.debug("logout : {}",user.getUserId());
        session.removeAttribute("user");
        log.debug("logout success");

        resp.sendRedirect("/index.jsp");
    }
}
