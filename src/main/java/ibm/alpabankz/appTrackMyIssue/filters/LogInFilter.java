package ibm.alpabankz.appTrackMyIssue.filters;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Component
@Order(1)
@WebFilter("/user/goToLogin")
public class LogInFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
        System.out.println("Filter LogInFilter is called");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    
}
