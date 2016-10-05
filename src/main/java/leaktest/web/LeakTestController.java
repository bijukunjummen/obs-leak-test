package leaktest.web;

import leaktest.domain.FullResponse;
import leaktest.service.FullResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeakTestController {

    @Autowired
    private FullResponseService fullResponseService;

    @RequestMapping("/full")
    public FullResponse fullResponse() {
        return fullResponseService.getFullResponse();
    }
}
