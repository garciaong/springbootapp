package com.garcia.springboot.app.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garcia.springboot.app.model.Player;
import com.garcia.springboot.app.service.IPlayerService;

@RequestMapping("/api/v1/player")
@RestController
public class PlayerController {

	private static final Logger log = LogManager.getLogger(PlayerController.class);
	
	@Autowired
	private IPlayerService playerService;
	
	@GetMapping
	public List<Player> getAllPlayers() {
		log.info("getAllPlayers()");
		return playerService.getAllPlayers();
	}
	
	@GetMapping(path="{id}")
	public Player getPlayer(@PathVariable("id") int id) {
		log.info("getPlayer()");
		return playerService.getPlayer(id);
	}
	
	@PostMapping
	public void addPlayer(@RequestBody Player player) {
		log.info("addPlayer()");
		playerService.addPlayer(player);
	}
	
	@PutMapping
	public String updatePlayer(@RequestBody Player player) {
		log.info("updatePlayer()");
		if(playerService.updatePlayer(player)) {
			return "Update completed";
		} else {
			return "Update failed";
		}
	}
}
