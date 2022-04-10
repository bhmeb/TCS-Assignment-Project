package web.microservice.userRegistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.microservice.userRegistration.model.User;
import web.microservice.userRegistration.repository.UserRepository;

@Service("userService")
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

 
    @Override
    public void register(User user) throws UserAlreadyExistException {

        if(checkIfUserExist(user.getUsername())){
            throw new UserAlreadyExistException("User already exists for this username");
        }
    }

    @Override
    public boolean checkIfUserExist(String userName) {
        return userRepository.findByName(userName) !=null ? true : false;
    }
}
