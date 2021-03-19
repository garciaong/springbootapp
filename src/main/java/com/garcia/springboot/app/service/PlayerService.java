package com.garcia.springboot.app.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.garcia.springboot.app.dao.IPlayerDao;
import com.garcia.springboot.app.model.Player;

@Service
public class PlayerService implements IPlayerService {
	
	private static final Logger log = LogManager.getLogger(PlayerService.class);
			
	@Autowired
	@Qualifier("mockPlayerDao")
	private IPlayerDao playerDao;
	
	@Override
	public Player getPlayer(int id) {
		log.info("getPlayer()");
		Optional<Player> player = playerDao.getPlayer(id);
		return player.isPresent()?player.get():null;
	}
	
	@Override
	public List<Player> getAllPlayers() {
		log.info("getAllPlayers()");
		return playerDao.getAllPlayers();
	}
	
	@Override
	public void addPlayer(Player player) {
		log.info("addPlayer()");
		playerDao.addPlayer(player);
	}
	
	@Override
	public boolean updatePlayer(Player player) {
		log.info("updatePlayer()");
		return playerDao.updatePlayer(player);
	}

}
