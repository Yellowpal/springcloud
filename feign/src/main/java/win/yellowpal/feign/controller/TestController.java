package win.yellowpal.feign.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import win.yellowpal.feign.client.FeignTestClient;

import java.util.List;

@RestController
@Slf4j
public class TestController {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FeignTestClient feignTestClient;

    @GetMapping("/test")
    public String test(){

        // 1.RestTemplate直接请求
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject("http://localhost:9080/test/json",String.class);

        // 2.LoadBalancerClient负载均衡
//        ServiceInstance serviceInstance = loadBalancerClient.choose("test");
//        String url = serviceInstance.getUri() + "/test/json";
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(url,String.class);

        // 3.RestTemplate + @LoadBalanced注解实现负载均衡
//        String result = restTemplate.getForObject("http://TEST/test/json",String.class);

        // 4. Feign
        String result = feignTestClient.test();
        return  result;
    }

    @GetMapping("/getById")
    public String getById(@RequestParam long id){
        return feignTestClient.getById(id);
    }

    @PostMapping("/post")
    public String post(@RequestBody List<Long> ids){

        return feignTestClient.post(ids);
    }
}
