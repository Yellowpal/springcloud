package win.yellowpal.eureka_server.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.amqp.rabbit.listener.ConsumeOkEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("/json")
	public String json(HttpServletRequest request, HttpServletResponse response){
		JsonObject object = new JsonObject();
		object.addProperty("id", 1231321);
		object.addProperty("name", "测试JSON");

		boolean hasCookies = false;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if("token".equals(cookie.getName())){
					hasCookies = true;
				}
			}
		}

		if(!hasCookies){
			Cookie cookie = new Cookie("token", UUID.randomUUID().toString());
			cookie.setPath("/");
			response.addCookie(cookie);
		}

		return object.toString();
	}
	
	@GetMapping("/getById")
	public String getById(long id){
		JsonObject object = new JsonObject();
		object.addProperty("id", id);
		object.addProperty("name", "测试getById");
		
		return object.toString();
	}
	
	@PostMapping("/post")
	public String post(@RequestBody List<Long> ids){
		JsonArray array = new JsonArray();
		for(long id : ids){
			JsonObject object = new JsonObject();
			object.addProperty("id", id);
			object.addProperty("name", "测试"+id);
			array.add(object);
		}
		
		return array.toString();
	}

}
