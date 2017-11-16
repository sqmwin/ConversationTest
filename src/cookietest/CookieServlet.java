package cookietest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 显示上一次访问此页面的访问时间
 * </p>
 *
 * @author sqm
 * @version 1.0
 */
@WebServlet(name = "CookieServlet", urlPatterns = "/cookieservlet")
public class CookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //通过request获得所有cookie
        Cookie[] cookies = request.getCookies();
        //建立要使用的cookie对象，便于操作
        Cookie lastVisit = null;
        //因为cookie是键值对形式，所以遍历后可进行操作、
        for (Cookie cookie : cookies) {
            //如果cookies中有name值为lastVisit的则把此cookie赋值给lastVisit
            if ("lastVisit".equals(cookie.getName())) {
                lastVisit = cookie;
            }
        }

        //判断lastVisit
        if (lastVisit == null) {
            //如果lastVisit的值还是为null，则cookies中没有此cookie，说明是第一次访问
            //输出欢迎在页面上
            response.getWriter().write("欢迎访问此页面");
        } else {
            //如果cookie有lastVisit，则已经访问过，拿取此cookie的值，转换成毫秒数字，打印在客户端浏览器（转成当地日期格式）
            //String(毫秒)→Long(毫秒)→Date(日期)→String(日期)
            Long lastVisitTime = Long.parseLong(lastVisit.getValue());
            //自动拆箱，把Long转成long（包装类型→基本类型）
            Date date = new Date(lastVisitTime);
            response.getWriter().write("上次访问的时间是：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        }

        //显示完后，把这次访问的时间记录在此cookie中，以便下次使用
        Cookie visitTime = new Cookie("lastVisit", String.valueOf(System.currentTimeMillis()));
        //把此cookie回写给客户端浏览器
        response.addCookie(visitTime);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doGet方法和doPost方法相同时使用
        this.doGet(request, response);


    }


}
