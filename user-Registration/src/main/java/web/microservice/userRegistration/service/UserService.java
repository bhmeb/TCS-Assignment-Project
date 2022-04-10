package web.microservice.userRegistration.service;

import web.microservice.userRegistration.model.User;

public interface UserService {

    void register(final User user) throws UserAlreadyExistException;
    boolean checkIfUserExist(final String userName);

}