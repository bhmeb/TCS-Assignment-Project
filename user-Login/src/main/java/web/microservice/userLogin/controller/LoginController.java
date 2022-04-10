package web.microservice.userLogin.controller;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import web.microservice.userLogin.model.User;
import web.microservice.userLogin.repository.UserReposotry;


@Controller
public class LoginController {

	ConcurrentMap<String, User> users = new ConcurrentHashMap<String, User>();
	
	@Autowired
	private UserReposotry userReposotry;

	/*@Autowired
	UserServiceDelegate userServiceDelegate;*/
	
	@Autowired
    RestTemplate restTemplate;
	
	/*@Value("${user.name:No such name available in property file}")
	private String userDetails;
	
	@GetMapping("/user")
	public String userDetails() {
		return "Welcome  : " +userDetails;
	}*/
	
	
	@GetMapping("")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/login")
	public String loginRoute(@RequestParam("userName") String userName,
							 @RequestParam("password") String password,Model model) {
		User u = null;
		try {
			u=userReposotry.findByName(userName);
		}catch (Exception e) {
			System.out.println("User not found !!!");
		}
		if(u!=null) {
			model.addAttribute("UserName", userName);
			return "index";
		}
		model.addAttribute("error","User Not Found, Kindly register !!!");
		return "login";
	}

	@GetMapping("/registration")
	public String ReistrationRoute() {
		return "registration";
	}
	
	
	
   public String ReistrationMicroservice(String fullName,String userName,String password,String phoneNo,Model model) {
 
        String response = restTemplate.getForObject("http://user-Registration/origin-reg-url/"+fullName+"/"+userName+"/"+password+"/"+phoneNo, String.class);
		model.addAttribute("Success","You have been registred successfully");
		
        //return "User Name -  " + fullName + " :::  " + " User Details " + response + " -  " + new Date();
		return "success";
    }
	
    @SuppressWarnings("unused")
	private String ReistrationMicroservice_Fallback(@RequestParam("fullName") String fullName,
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("phoneNo") String phoneNo,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model) {
        System.out.println("User registration page Service is down!!! fallback route enabled...");
        return "No Response From Login Service at this moment. " +  " Service will be back shortly - " + new Date();
        //return "errorpage";
    }
    
    
	@PostMapping("/insert-new-user")
	@HystrixCommand(fallbackMethod = "ReistrationMicroservice_Fallback")
	public String ReistrationMicroservice(@RequestParam("fullName") String fullName,
			                              @RequestParam("userName") String userName,
			                              @RequestParam("password") String password,
			                              @RequestParam("phoneNo") String phoneNo,
			                              @RequestParam("confirmPassword") String confirmPassword,
			                              Model model) {

		if(password.equals(confirmPassword)) {
			restTemplate.getForObject("http://user-Registration/origin-reg-url/"+fullName+"/"+userName+"/"+password+"/"+phoneNo,
					String.class);
			model.addAttribute("Success","You have been registred successfully");
			System.out.println("In LoginController controller");
			model.addAttribute("successMessage", "Registration is successful!!!!!!");
			return "success";
		}else {
			model.addAttribute("Error", "Both password doesn't match..please try again!!");
		}
			
		return "login";
	}	
}