package web.microservice.userLogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.microservice.userLogin.model.User;

public interface UserReposotry extends JpaRepository<User, Integer>{

		User findByName(String userName);

	}

