package win.yellowpal.hystrix.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "test", fallback = FeignTestClientFallback.class)
public interface FeignTestClient {

    @GetMapping("/test/json")
    String test();

    @GetMapping("/test/getById")
    String getById(@RequestParam("id") long id);

    @PostMapping("/test/post")
    String post(@RequestBody List<Long> ids);

}
@Component
class FeignTestClientFallback implements FeignTestClient{

    @Override
    public String test() {
        return "fallback";
    }

    @Override
    public String getById(long id) {
        return "fallback";
    }

    @Override
    public String post(List<Long> ids) {
        return "fallback";
    }
}
