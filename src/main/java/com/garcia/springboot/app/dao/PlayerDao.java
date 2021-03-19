package com.garcia.springboot.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.garcia.springboot.app.model.Player;

@Repository("mockPlayerDao")
public class PlayerDao implements IPlayerDao {

	private static final Logger log = LogManager.getLogger(PlayerDao.class);
	private static List<Player> players = new ArrayList<Player>(); 
	
	@Override
	public Optional<Player> getPlayer(int id) {
		log.info("getPlayer()");
		Optional<Player> player = players.stream().filter(p->p.getId()==id).findFirst();
		return player;
	}
	
	@Override
	public List<Player> getAllPlayers() {
		log.info("getAllPlayers()");
		return players;
	}
	
	@Override
	public void addPlayer(Player player) {
		log.info("addPlayer()");
		players.add(player);
	}
	
	@Override
	public boolean updatePlayer(Player player) {
		log.info("updatePlayer()");
		return getPlayer(player.getId()).map(
				p -> {
					int playerIndex = players.indexOf(p);
					log.debug("Index={}", playerIndex);
					if(playerIndex >= 0) {
						players.set(playerIndex, player);
						return true;
					} else {
						return false;
					}
				}).orElse(false);
	}

}
