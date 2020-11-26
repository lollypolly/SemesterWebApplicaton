package ru.itis.servlets;

import ru.itis.dto.SignUpForm;
import ru.itis.services.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/p/signUp")
public class SignUpServlet extends HttpServlet {


    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signUpService = (SignUpService) config.getServletContext().getAttribute("signUpService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher(request.getContextPath() + "/signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        SignUpForm form = new SignUpForm();
        form.setLastName(request.getParameter("lastName"));
        form.setFirstName(request.getParameter("firstName"));
        form.setUsername(request.getParameter("username"));
        form.setEmail(request.getParameter("email"));
        form.setPassword(request.getParameter("password"));

        request.getSession().setAttribute("id", signUpService.signUp(form));
        request.getSession().setAttribute("firstName", form.getFirstName());
        request.getSession().setAttribute("lastName", form.getLastName());
        request.getSession().setAttribute("username", form.getUsername());

        response.sendRedirect("/p/explore");
    }
}

