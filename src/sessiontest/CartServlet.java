package sessiontest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 购物车结算程序
 * </p>
 *
 * @author sqm
 * @version 1.0
 */
@WebServlet(name = "CartServlet", urlPatterns = "/cartservlet")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //先获取?后面的参数→id
        String id = request.getParameter("id");
        //设定id值对应的商品名(数据库)
        String[] names = {"CPU", "主板", "内存", "显卡", "硬盘"};
        int index = Integer.parseInt(id);
        //得到对应商品名称
        String name = names[index];
        

        //获取对应浏览器的session
        HttpSession session = request.getSession();
        //所需的session域中对象name是"cart" ,value是商品名=数量的键值对
        //购物车是Map形式的键值对name=count
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");

        //判断是不是第一次访问,如果是就建立购物车
        if (cart == null) {
            //建立购物车
            cart = new HashMap<String, Integer>();
            //把商品名=数量添加入购物车
            cart.put(name, 1);
        } else {
            //如果不是第一次访问,就判断是否包含此商品
            if (cart.containsKey(name)) {
                //如果有此商品,获得数量,+1,放回数量
                //count自动拆箱(Integer→int)
                int count = cart.get(name);
                count++;
                cart.put(name, count);
            } else {
                //如果没有此商品,则购物车name=1
                cart.put(name, 1);
            }
        }

        //把购物车数据存入session
        session.setAttribute("cart", cart);

        //给用户提示
        response.getWriter().println("<h3>商品已经加入购物车!</h3><h3><a href='/test/jsp/shopTest.jsp'>点此返回</a></h3><h3><a href='/test/jsp/cartTest.jsp'>点此结算</a></h3>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doGet方法和doPost方法相同时使用
        this.doGet(request, response);


    }


}
