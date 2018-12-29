package win.yellowpal.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(value = "test")
public interface FeignTestClient {

    @GetMapping("/test/json")
    String test();

    @GetMapping("/test/getById")
    String getById(@RequestParam("id") long id);

    @PostMapping("/test/post")
    String post(@RequestBody List<Long> ids);

}
