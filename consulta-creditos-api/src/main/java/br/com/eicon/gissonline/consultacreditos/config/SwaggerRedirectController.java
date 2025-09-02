package br.com.eicon.gissonline.consultacreditos.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerRedirectController {
  @GetMapping("/swagger-ui.html")
  public String redirect() {
    return "redirect:/swagger-ui/index.html";
  }
}
