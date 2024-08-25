package service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GradingController {
  @GetMapping("/grade/{studentid}/{courseid}")
  public String getGrade(@PathVariable("studentid") String studentid,  
                         @PathVariable("courseid") String courseid) {
    return "A+";
  }
}
