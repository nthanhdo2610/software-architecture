package service;



import org.slf4j.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
	private static final Logger logger = LoggerFactory.getLogger(StockController.class.getName());

    @RequestMapping("/text")
    public String getText() {
        return "World";
    }

}
