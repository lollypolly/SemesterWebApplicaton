package ru.itis.listeners;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.repositories.*;
import ru.itis.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class CustomServletContextListener implements ServletContextListener {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/kotopoisk";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "fioletovobu27";
    private static final String DB_DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        FilesRepository filesRepository = new FilesRepositoryImpl(dataSource);
        SearchRepository searchRepository = new SearchRepositoryImpl(dataSource);
        LikeRepository likeRepository = new LikeRepositoryImpl(dataSource);

        SignUpService signUpService = new SignUpServiceImpl(usersRepository);
        SignInService signInService = new SignInServiceImpl(usersRepository);//создала
        CatService catService = new CatServiceImpl(likeRepository);
        SearchService searchService = new SearchServiceImpl(searchRepository);
        FilesService filesService = new FilesServiceImpl(filesRepository);

        servletContext.setAttribute("signUpService", signUpService);//добавила
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("catService", catService);
        servletContext.setAttribute("searchService", searchService);
        servletContext.setAttribute("filesService", filesService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
