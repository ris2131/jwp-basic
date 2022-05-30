package next.web;

import core.db.DataBase;
import next.support.context.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginUserServlet extends HttpServlet {
    public static final Logger log = LoggerFactory.getLogger(LoginUserServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("login button");
        User user = DataBase.findUserById(req.getParameter("userId"));
        if(user != null){//has user
            if(req.getParameter("password").equals(user.getPassword())){//same password
                log.debug("login success : {}, {}",req.getParameter("password"),user.getPassword());
                //login 되면 세션 처리 해줘야 함.
                //아하 세션을 request 에서 가져 와야하는구나.
                HttpSession session = req.getSession();
                session.setAttribute("user",user);//User user 객체를 "user"라는 이름으로 attribute 설정.

            }
        }
        //세션처리라 redirect 가능
        resp.sendRedirect("/index.jsp");//임시.. 절대경로
    }
}
