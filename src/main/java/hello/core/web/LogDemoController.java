package hello.core.web;

import hello.core.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
    // private final ObjectProvider <MyLogger> myLoggerObjectProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String URL = request.getRequestURL().toString();
      //  MyLogger myLogger = myLoggerObjectProvider.getObject();
        myLogger.setRequestURL(URL);

        myLogger.log("Controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
