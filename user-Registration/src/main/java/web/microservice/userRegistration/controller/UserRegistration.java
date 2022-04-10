package web.microservice.userRegistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.microservice.userRegistration.model.User;
import web.microservice.userRegistration.repository.UserRepository;
import web.microservice.userRegistration.service.UserService;


@RestController
public class UserRegistration {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	/*@Value("${my.app:No such name available in property file}")
	private String appDetails;
	
	@GetMapping("/app")
	public String getApp() {
		return "Welcome to : " +appDetails;
	}
	
	ConcurrentMap<String, User> users = new ConcurrentHashMap<String, User>();
	
	@GetMapping("/all")
    public List<User> getAllUsers() {
		return new ArrayList<User>(users.values());
	}
	*/
	
	@RequestMapping("/origin-reg-url/{fullName}/{userName}/{password}/{phoneNo}")
	public String registerUser(@PathVariable("fullName") String fullName,
			                   @PathVariable("userName") String userName,
			                   @PathVariable("password") String password,
			                   @PathVariable("phoneNo") String phoneNo,
			                   Model model) {

		User user=new User();
		boolean R = userService.checkIfUserExist(userName);
		if(!R) {
			user.setId(3);
			user.setName(fullName);
			user.setUsername(userName);
			user.setPassword(password);
			user.setPhoneno(phoneNo);
			userRepository.save(user);
			model.addAttribute(userName, "Successfully registered" + userName);
			System.out.println("In UserRegistration controller");
			return "Successfully registered";
			
		}else if(R) {
			model.addAttribute(userName, "There is already an account registered with the name" + userName);
			return "Account already exists";
		}
		else {
			return "Error in registration !!!";
		}
	}
}