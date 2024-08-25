package service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
  @GetMapping("/student/{studentid}")
  public String getName(@PathVariable("studentid") String studentid) {
    return "Frank Brown";
  }
}
