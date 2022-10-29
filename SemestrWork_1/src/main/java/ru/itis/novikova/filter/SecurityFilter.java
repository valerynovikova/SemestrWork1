package ru.itis.novikova.filter;

import ru.itis.novikova.service.SecurityService;
import ru.itis.novikova.service.SecurityServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/profile","/addReport", "/reportInfo", "/userInfo", "/addArticle", "/articleInfo"})
public class SecurityFilter implements Filter {

	private SecurityService securityService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.securityService = new SecurityServiceImpl();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (!securityService.isSigned(req)) {
			resp.sendRedirect("/signIn");
			return;
		}



		HttpSession session = req.getSession(false);
		req.setAttribute("user", session.getAttribute("user"));

		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {

	}
}
