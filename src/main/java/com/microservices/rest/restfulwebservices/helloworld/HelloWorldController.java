package com.microservices.rest.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	//GET
	//URI - /hello-world
	//method - "Hello World"
	
	/*@RequestMapping(method=RequestMethod.GET, path="hello-world")
	public String helloWorld() {
		return "Hello RequestMapping";
	}*/
	
	@GetMapping(path="hello-world")
	public String helloWorld() {
		return "Hello GetMapping";
	}
	
	@GetMapping(path="hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World My Bean");
	}
	
	//hello-world/path-variable/in28minutes
	@GetMapping(path="hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		//return new HelloWorldBean(name);
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	
	@GetMapping(path="hello-world-internationalized")
	public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}
	
/*	THis is not needed if spring.messages.basename=messages is declared in application.properties
 * 
 * @GetMapping(path="hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}*/
}
