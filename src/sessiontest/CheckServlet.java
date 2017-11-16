package sessiontest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author sqm
 * @version 1.0
 */
@WebServlet(name = "CheckServlet", urlPatterns = "/checkservlet")
public class CheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //获取session中的验证码信息
        String codeText = (String) request.getSession().getAttribute("verificationCode");
        //获取表单中输入的验证码
        String thisCodeText = request.getParameter("verificationCode");
        //对比验证码是否正确
        if (thisCodeText == null || !thisCodeText.toLowerCase().equals(codeText)) {
            //如果输入的验证码错误或者为null,则转发回原页面并提示"验证码输入错误"
            //为什么用转发不用重定向?因为转发是一次请求一次响应可以携带信息回原页面
            request.setAttribute("msg", "验证码输入错误");
            //转发的路径是服务器内的路径
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
        //如果输入验证码正确,则检查用户名和密码是否正确
        else if (thisCodeText.toLowerCase().equals(codeText)) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String rightName = "admin";
            String rightPassword = "admin";
            //用户名和密码正确,则重定向至登陆成功页面
            if (username.equals(rightName) && password.equals(rightPassword)) {
                response.sendRedirect("/test/jsp/welcome.jsp");
            } else {
                //用户名和密码错误,则转发回原页面并提示"用户名和密码错误:
                request.setAttribute("msg", "用户名和密码错误");
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            }
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doGet方法和doPost方法相同时使用
        this.doGet(request, response);


    }


}
