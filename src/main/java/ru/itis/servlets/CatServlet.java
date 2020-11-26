package ru.itis.servlets;

import org.json.JSONArray;
import ru.itis.services.CatService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cat") // test
public class CatServlet extends HttpServlet {

    private CatService catService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        catService = (CatService) config.getServletContext().getAttribute("catService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        List<String> ids = catService.getLikedImages((Integer) request.getSession(false).getAttribute("id"));
        JSONArray arr = new JSONArray();
        for (String s : ids) {
            arr.put(catService.getImageByID(s));
        }

        response.getWriter().write(arr.toString());
        response.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(catService.getAllCats(Integer.parseInt(request.getHeader("limit")),
                Integer.parseInt(request.getHeader("page"))).toString());
    }
}
