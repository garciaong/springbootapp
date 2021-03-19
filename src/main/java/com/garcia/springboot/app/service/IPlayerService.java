package com.garcia.springboot.app.service;

import java.util.List;

import com.garcia.springboot.app.model.Player;

public interface IPlayerService {

	public Player getPlayer(int id);
	
	public List<Player> getAllPlayers();
	
	public void addPlayer(Player player);
	
	public boolean updatePlayer(Player player);
}
