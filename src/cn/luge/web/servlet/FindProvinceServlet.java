package cn.luge.web.servlet;

import cn.luge.domain.Province;
import cn.luge.service.ProvinceService;
import cn.luge.service.impl.ProvinceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findProvinceServlet")
public class FindProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProvinceService service = new ProvinceServiceImpl();
//        List<Province> list = service.findAll();
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(list);
        String json = service.findAllJson();
        System.out.println(json);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
