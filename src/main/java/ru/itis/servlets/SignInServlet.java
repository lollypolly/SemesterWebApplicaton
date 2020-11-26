package ru.itis.servlets;

import ru.itis.dto.SignInForm;
import ru.itis.models.User;
import ru.itis.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/p/signIn")
public class SignInServlet extends HttpServlet {
    private SignInService signInService;

    @Override
    public void init(ServletConfig config) {
        signInService = (SignInService) config.getServletContext().getAttribute("signInService");
    }

    @Override
    //получает данные с сервера
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher(request.getContextPath() + "/signIn.jsp").forward(request, response);
    }

    @Override
    //отправляет данные на сервер (скрывает параметры)

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        SignInForm form = new SignInForm();
        form.setUsername(request.getParameter("username"));
        form.setEmail(request.getParameter("email"));
        form.setPassword(request.getParameter("password"));


        // localhost:8080/signIn?username=Abc&password=123
        User id = signInService.signIn(form);
        if (id != null) {
            HttpSession session = request.getSession(); // объект, который хранится
            // до того момента, пока ты не закрыла браузер
            session.setAttribute("id", id.getId());
            session.setAttribute("firstName", id.getFirstName());
            session.setAttribute("lastName", id.getLastName());
            session.setAttribute("username", id.getUsername());

            //под ключем id устанавливает атрибут
            response.sendRedirect(request.getContextPath() + "/p/explore");
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
