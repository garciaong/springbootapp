package com.garcia.springboot.app.dao;

import java.util.List;
import java.util.Optional;

import com.garcia.springboot.app.model.Player;

public interface IPlayerDao {

	public Optional<Player> getPlayer(int id);
	
	public List<Player> getAllPlayers();
	
	public void addPlayer(Player player);
	
	public boolean updatePlayer(Player player);
}
