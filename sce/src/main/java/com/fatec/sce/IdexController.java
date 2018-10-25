package com.fatec.sce;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IdexController {
  
 
    @GetMapping("/")
    public String homePage(Model model) {
       
        return "index";
    }
}