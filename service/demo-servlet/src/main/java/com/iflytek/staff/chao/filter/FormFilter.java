package com.iflytek.staff.chao.filter;


import java.io.IOException;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "FormFilter", servletNames = {"FormServlet"})
public class FormFilter implements Filter {
    public FormFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        Map<String, String[]> params = request.getParameterMap();

        params.keySet().stream().forEach(key -> {

            String value = params.get(key)[0];

            if (key != null && ! value.trim().isEmpty())
                request.setAttribute(key, params.get(key)[0]);

        });

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }
}
