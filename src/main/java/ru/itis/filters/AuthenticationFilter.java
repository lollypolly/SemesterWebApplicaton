  package ru.itis.filters;
  
  import ru.itis.dto.UserDto;
  import ru.itis.services.UsersService;
  
  import javax.servlet.*;
  import javax.servlet.annotation.WebFilter;
  import javax.servlet.http.Cookie;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import javax.servlet.http.HttpSession;
  import java.io.IOException;
  import java.util.Optional;
  
  @WebFilter("/p/*")
  public class AuthenticationFilter implements Filter {
  
      @Override
      public void init(FilterConfig filterConfig) throws ServletException {
  
      }
  
      @Override
      public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            // преобразуем запросы к нужному виду
          HttpServletRequest request = (HttpServletRequest) servletRequest;
          HttpServletResponse response = (HttpServletResponse) servletResponse;
          HttpSession session = request.getSession(false); // получаю сессию если ее нет то не создаю новую
  
          Boolean isAuthenticated = false;
          Boolean sessionExists = session != null;
          Boolean isHomePage = (request.getRequestURI().equals("/") || request.getRequestURI().equals("/p/signIn") || request.getRequestURI().equals("/p/signUp"));
  
          System.out.println(request.getRequestURI());
          if (sessionExists) {
              if (session.getAttribute("id") == null) {
                  isAuthenticated = false;
              } else {
                  isAuthenticated = (Integer) session.getAttribute("id") > 0;
              }
          }
  
            // если авторизован и запрашивает не домашнюю стр или если не авторизован и запрашивает домашнюю
          if (isAuthenticated && !isHomePage || !isAuthenticated && isHomePage) {
            //     отдаем ему то, что он хочет
              filterChain.doFilter(request, response);
          } else {
             //    если пользователь не аутенцицицирован и запрашивает другие страницы
              response.sendRedirect("/");
          }
      }
  
      @Override
      public void destroy() {
  
   }
}
