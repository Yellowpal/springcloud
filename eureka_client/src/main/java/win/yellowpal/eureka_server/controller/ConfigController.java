package win.yellowpal.eureka_server.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.yellowpal.eureka_server.config.BusConfig;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private BusConfig busConfig;

    @GetMapping("/test")
    public String test(){
        return busConfig.getValue();
    }
}
