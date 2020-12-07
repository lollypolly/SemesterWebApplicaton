package ru.itis.servlets;

import org.json.JSONObject;
import ru.itis.services.CatService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {

    private CatService catService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        catService = (CatService) config.getServletContext().getAttribute("catService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                response.getOutputStream(), StandardCharsets.UTF_8), true);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        boolean like = false;
        if (request.getSession(false).getAttribute("id") != null) {

            //если в header есть значение isLiked то мы выполняем условие
            if(request.getHeader("isLiked") != null){

                //условие
                like = catService.imageLiked(Integer.parseInt(request.getSession(false).getAttribute("id").toString()),
                        request.getHeader("isLiked"));
            }

            if(request.getHeader("like") != null){
                like = catService.likeImage(Integer.parseInt(request.getSession(false).getAttribute("id").toString()),
                        request.getHeader("like"));
            }
        }

        out.print(like);
        out.flush();

    }
}
