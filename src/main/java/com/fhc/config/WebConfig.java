package com.fhc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fhc.converter.LocalDateTimeToStringConverter;
import com.fhc.converter.LocalDateToStringConverter;
import com.fhc.converter.StringToLeadNoteListConverter;
import com.fhc.converter.StringToLocalDateConverter;
import com.fhc.converter.StringToLocalDateTimeConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		// registry.addConverter(new StringToRoleConverter());
		// registry.addConverter(new StringToLoadTypeConverter());
		// registry.addConverter(new StringToVehicleTypeConverter());
		registry.addConverter(new StringToLeadNoteListConverter());
		registry.addConverter(new StringToLocalDateTimeConverter());
		registry.addConverter(new LocalDateTimeToStringConverter());
		registry.addConverter(new LocalDateToStringConverter());
		registry.addConverter(new StringToLocalDateConverter());
	}

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:9091")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("login").setViewName("signin");
		// registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
}