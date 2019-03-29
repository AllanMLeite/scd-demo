package br.com.scd.demo.config;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class SwaggerApi {

	@GetMapping(value = "/")
	public void index(HttpServletResponse response) throws IOException {
		response.sendRedirect("swagger-ui.html");
	}
}