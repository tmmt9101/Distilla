package com.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.models.ProductDao;
import java.util.List;
import com.models.*;


@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private DataSource source;
    private ProductDao productDao;
    public ProductServlet() {
        super();
    }
    public void init() throws ServletException {
        try {
            InitialContext context = new InitialContext();
            source = (DataSource) context.lookup("java:/comp/env/jdbc/database");
            productDao = new ProductDao(source);
        } catch (Exception e) {
            throw new ServletException("NIGGADBFUCKEDUP");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Product> products = this.productDao.getAll();
            var w        = response.getWriter();
            response.setContentType("text/html");
            w.println("<html>");
            w.println("<body>");
             w.println("<ul>");
             if (products.size() > 0) {
                 for (var product : products) {
                     w.println("    <li>" +
                               product.getName() +
                               "</li>");
                 }
             } else {
                 w.println("NOTHING");
             }
             w.println("</ul>");
             w.println("</html>");
             w.println("</body>");
        } catch (SQLException exception) {
            var writer = response.getWriter();
            writer.println("EXCEPTION MESSAGE: "+ exception.getMessage());
            writer.println("EXCEPTION TYPE: " + exception.getClass().getName());
            writer.print("EXCEPTION STACK: ");
            exception.printStackTrace(writer);
         }
    }
}

