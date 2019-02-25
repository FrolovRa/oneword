package app;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;


import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpSession;

@WebFilter(
        urlPatterns = { "/*"}
)
public class AuthFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) servletRequest;

            HttpSession session = request.getSession(true);
            String authHeader = (String) session.getAttribute("role");
            if (authHeader == null) {
                try {
                    session.setAttribute("role", "guest");
                    filterChain.doFilter(servletRequest, servletResponse);
                } catch (UnsupportedEncodingException e) {
                    throw new Error("Couldn't retrieve authentication", e);
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }

        @Override
        public void destroy() {
        }
}
