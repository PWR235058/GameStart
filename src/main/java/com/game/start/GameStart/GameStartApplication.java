package com.game.start.GameStart;

import com.game.start.GameStart.entity.User;
import com.game.start.GameStart.jpa.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
public class GameStartApplication {
	public static void main(String[] args) {
		SpringApplication.run(GameStartApplication.class, args);
	}

}
