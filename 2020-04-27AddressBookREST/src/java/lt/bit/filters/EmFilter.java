package lt.bit.filters;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
public class EmFilter implements Filter {

    private EntityManagerFactory emf;

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
        try {
            emf = Persistence.createEntityManagerFactory("AddressBookRESTPU");
        } catch (Exception e) {
            throw new ServletException("Failed to create Entity Manager Factory", e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            request.setAttribute("em", em);
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw new ServletException("failed to create entity manager", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void destroy() {
        if (emf != null) {
            emf.close();
        }
    }

}
