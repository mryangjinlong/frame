package com;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.model.Company;
import com.model.Employee;
import com.service.CompanyService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
//ApplicationContextAware
@Controller("webController")
@RequestMapping("first")
public class WebController {


    private CompanyService companyService;

	String springServletName = "springMvc";    //配置的spring的servlet的名称 （servlet-name）
	String WebApplicationContextKey = "org.springframework.web.servlet.FrameworkServlet.CONTEXT." + springServletName;
	
	 @RequestMapping("/hello")
	 public ModelAndView helloWorld(@RequestParam("id")Long companyId , HttpServletRequest request , Model model ,
                                    Boolean flag , Company company) {
//         System.out.println("show me the money");
         company.setId(companyId);
//         Boolean aaa = companyService.add(company);
         System.out.println(companyId);
         System.out.println(company.getLoginName());
         String message = "Hello World, Spring 3.0!     " + companyId + "       "+ company.getLoginName();
	  return new ModelAndView("index", "message", message);
	 }

    @RequestMapping("/hello2")
    public String helloWorld2(HttpServletRequest request , Model model,Boolean flag) {
        String message = "Hello World, Spring 3.0!";
        model.addAttribute("message","hello2");
        return "index";
    }

    @RequestMapping("/hello3")
    public String helloWorld3(HttpServletRequest request , Model model,Boolean flag) {
        model.addAttribute("message","hello3");
        return "redirect:hello2.html";
    }

    @RequestMapping("/hello4/{id}")
    public String helloWorld4(@PathVariable Long id , HttpServletRequest request , Model model, Boolean flag) {
        model.addAttribute("message",id);
        return "redirect:hello2.html";
    }

    @RequestMapping(params = "method=helloword5")
    public String helloWorld5(@PathVariable Long id , HttpServletRequest request , Model model, Boolean flag) {
        model.addAttribute("message",id);
        return "redirect:hello2.html";
    }

    @RequestMapping("hello6")
    @ResponseBody
    public String helloWorld6(){
        return "aaaa";
    }

    /*
    * 需要jackson相关的jar包
    * */
    @RequestMapping("json")
    @ResponseBody
    public Company json(){
        return new Company(11l,"long",new Employee());
    }

/*	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		
	}*/

    public CompanyService getCompanyService() {
        return companyService;
    }

    @Resource
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
}
