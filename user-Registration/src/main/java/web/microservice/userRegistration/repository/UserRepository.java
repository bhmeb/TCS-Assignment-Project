package web.microservice.userRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.microservice.userRegistration.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByName(String userName);

}