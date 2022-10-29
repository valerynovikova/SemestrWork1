package ru.itis.novikova.filter;


import ru.itis.novikova.service.SecurityService;
import ru.itis.novikova.service.SecurityServiceImpl;
import ru.itis.novikova.service.UserService;
import ru.itis.novikova.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/info"})
public class StartFilter implements Filter {

	private SecurityService securityService;

	private UserService userService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		securityService = new SecurityServiceImpl();
		userService = new UserServiceImpl();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		if (securityService.isSigned(req)) {
			HttpSession session = req.getSession();
			Cookie[] cookies = req.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(SecurityServiceImpl.USER_AUTH_COOKIE_NAME)) {
					session.setAttribute("user", userService.findUserByLogin(cookie.getValue()));
				}
			req.setAttribute("user", session.getAttribute("user"));
			}
		}
		chain.doFilter(req, response);
	}

}
