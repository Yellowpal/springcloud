package win.yellowpal.eureka_server.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("/json")
	public String json(){
		JsonObject object = new JsonObject();
		object.addProperty("id", 1231321);
		object.addProperty("name", "测试JSON");
		
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
