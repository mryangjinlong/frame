package com.log4j;

import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

public class Log4jTest extends HttpServlet {

	private static final long serialVersionUID = -7236079171857431570L;

	public void init(ServletConfig config) throws ServletException {
		String log4jFile = config.getServletContext().getRealPath("/") + "/WEB-INF/classes/log4j.properties";
		Enumeration names = config.getInitParameterNames();
		while(names.hasMoreElements()) {
			String key = (String)names.nextElement();
			String value = config.getInitParameter(key);
			System.setProperty(key, value);
		}
		PropertyConfigurator.configure(log4jFile);
	}

}
