package win.yellowpal.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import win.yellowpal.hystrix.client.FeignTestClient;

@RestController
@RequestMapping("/test")
@DefaultProperties(defaultFallback = "defaultFallback")
public class TestController {

    @Autowired
    FeignTestClient feignTestClient;

    @GetMapping("/test")
//    超时配置
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    })
//    熔断配置
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
//    })
    @HystrixCommand(commandKey = "TestController_test")
    public String test(@RequestParam(name = "step") int step){
        try {
            if(step % 2 == 0){
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hystrix";
    }

    @GetMapping("/exception")
    @HystrixCommand(fallbackMethod = "fallback")
    public String exception(){
        int i = 1/0;

        return "";
    }

    @GetMapping("/feign")
    public String feign(){
        String str = feignTestClient.getById(10);
        return str;
    }

    private String fallback(){
        return "系统繁忙";
    }

    private String defaultFallback(){
        return "系统繁忙!!!";
    }

}
