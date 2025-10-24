package com.fhc.controller;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

	@Autowired
	private ErrorAttributes errorAttributes;

	private static final String PATH = "/error";

	@RequestMapping(PATH)
	public ModelAndView handleException(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		ServletWebRequest servletWebRequest = new ServletWebRequest(request);
		Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(servletWebRequest,
				ErrorAttributeOptions.defaults().including(ErrorAttributeOptions.Include.STACK_TRACE));

		mv.addObject("status", errorAttributes.get("status"));
		mv.addObject("error", errorAttributes.get("error"));

		mv.setViewName("common/noautherror");

		return mv;
	}
}
