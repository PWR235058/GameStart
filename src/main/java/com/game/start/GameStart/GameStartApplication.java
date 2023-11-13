package com.game.start.GameStart;

import com.game.start.GameStart.entity.Osoby;
import com.game.start.GameStart.jpa.OsobyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
public class GameStartApplication {
	@Autowired
	OsobyRepository osoby;
	@GetMapping("/")
	public List<Osoby> index(){
		return osoby.findAll();
	}
	@GetMapping("/dodaj")
	public void dodawanie(){
		//osoby.save(new Osoby(0,"imie","naz"));
	}
	public static void main(String[] args) {
		SpringApplication.run(GameStartApplication.class, args);
	}

}
