package lt.bit.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author Gabrielius
 */
@WebFilter(filterName = "ConnFilter", urlPatterns = {"/*"})
public class ConnFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            throw new ServletException("Failed to load JDBC driver", e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?serverTimezone=UTC&characterEncoding=UTF-8", "root", "root")) {
            request.setAttribute("conn", conn);
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw new ServletException("Failed to connect to DB", e);
        }
    }

    @Override
    public void destroy() {
    }

}
