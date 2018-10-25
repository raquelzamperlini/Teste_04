package com.fatec.sce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class SceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SceApplication.class, args);
	}
	@GetMapping("/")
    public String homePage(Model model) {
       
        return "index";
    }
}
