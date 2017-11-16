package sessiontest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author sqm
 * @version 1.0
 */
@WebServlet(name = "CleanAllServlet", urlPatterns = "/cleanallservlet")
public class CleanAllServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //得到session域对象
        HttpSession session = request.getSession();
        //删除session域对象中的name为"cart"的对象
        session.removeAttribute("cart");
        //删除后跳转回购物车结算页面
        response.sendRedirect("/test/jsp/cartTest.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doGet方法和doPost方法相同时使用
        this.doGet(request, response);


    }


}
