package com.game.start.GameStart;

import com.game.start.GameStart.entity.User;
import com.game.start.GameStart.jpa.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@SpringBootApplication
public class GameStartApplication {
	@GetMapping("/")
	public String index(){
		return "index.html";
	}
	@GetMapping("/login")
	public String login(){
		return "login.html";
	}
	@GetMapping("/register")
	public String rejestracja(){
		return "register.html";
	}
	public static void main(String[] args) {
		SpringApplication.run(GameStartApplication.class, args);
	}

}
