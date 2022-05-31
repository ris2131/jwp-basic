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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/update")
public class UpdateUserFormServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("doGet: user/updateGET");
        //세션 가져오기(나중에 이런거 인터셉터 할수 있겠지.)
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        //유저 로그인 되어있고
        if(user != null){
            //디비 가져오기
            User modifyUser = DataBase.findUserById(req.getParameter("userId"));
            //같은 ID 면 보내주기.
            if(user.getUserId().equals( modifyUser.getUserId())){
                log.debug("userid : {} , userPwd : {}",user.getUserId(),user.getPassword());
                req.setAttribute("user",user);
                RequestDispatcher rd = req.getRequestDispatcher("/user/updateform.jsp");
                rd.forward(req,resp);
                return;//이렇게 분기 하는게 맞나...
            }
        }

        log.error("건들면 안되는 정보. 예외처리 해주자.");
        resp.sendRedirect("/index.jsp");
        return;
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
