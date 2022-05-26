package next.web;

import core.db.DataBase;
import next.support.context.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/update")
public class UpdateUserFormServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("doGet: user/updateGET");
        User user = DataBase.findUserById(req.getParameter("userId"));
        log.debug("userid : {} , userPwd : {}",user.getUserId(),user.getPassword());

        req.setAttribute("user",user);
        RequestDispatcher rd = req.getRequestDispatcher("/user/updateform.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("doGet: user/updatePOST");
        User updateUser = new User(req.getParameter("updateUserId"),
                req.getParameter("updatePassword"),
                req.getParameter("updateName"),
                req.getParameter("updateEmail"));

        DataBase.updateUser(req.getParameter("userId"),updateUser);

        resp.sendRedirect("/user/list");
    }
}
