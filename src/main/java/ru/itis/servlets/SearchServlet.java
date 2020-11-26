package ru.itis.servlets;

import ru.itis.services.SearchService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private SearchService searchService;

    @Override
    public void init(ServletConfig config) throws ServletException {

        searchService = (SearchService) config.getServletContext().getAttribute("searchService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(searchService.getSearchCats(request.getParameter("word")).toString());
    }
}
