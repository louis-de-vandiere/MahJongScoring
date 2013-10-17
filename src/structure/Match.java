package structure;

import java.util.LinkedList;



public class Match {
	LinkedList<Game> gameList;
	Player p1;
	Player p2;
	Player p3;
	Player p4;
	
	public Match(String east, String south, String west, String north) {
		gameList = new LinkedList<Game>();
		p1 = new Player(east);
		p2 = new Player(south);
		p3 = new Player(west);
		p4 = new Player(north);
		p1.setTotalScore(0);
		p2.setTotalScore(0);
		p3.setTotalScore(0);
		p4.setTotalScore(0);
	}
	
	public void setName(String p1, String p2, String p3, String p4){
		this.p1.setName(p1);
		this.p2.setName(p2);
		this.p3.setName(p3);
		this.p4.setName(p4);
	}
	public LinkedList<Game> getGameList(){
		return gameList;
	}
	
	public void newGame(){
		Game g = turnWind();
		gameList.add(g);
	}
	
	public Player getP1() {
		return p1;
	}

	public Player getP2() {
		return p2;
	}

	public Player getP3() {
		return p3;
	}

	public Player getP4() {
		return p4;
	}
	public void score(){
		Game g = gameList.getLast();
		p1.setTotalScore(p1.getTotalScore() + g.getPlayerByName(p1.getName()).getScore());
		p2.setTotalScore(p2.getTotalScore() + g.getPlayerByName(p2.getName()).getScore());
		p3.setTotalScore(p3.getTotalScore() + g.getPlayerByName(p3.getName()).getScore());
		p4.setTotalScore(p4.getTotalScore() + g.getPlayerByName(p4.getName()).getScore());
	}
	
	public void score(int index, int eastScore, int southScore, int westScore, int northScore, Wind mahJong){
		Game g = gameList.get(index);
		p1.setTotalScore(p1.getTotalScore() - g.getPlayerByName(p1.getName()).getScore());
		p2.setTotalScore(p2.getTotalScore() - g.getPlayerByName(p2.getName()).getScore());
		p3.setTotalScore(p3.getTotalScore() - g.getPlayerByName(p3.getName()).getScore());
		p4.setTotalScore(p4.getTotalScore() - g.getPlayerByName(p4.getName()).getScore());
		g.score(eastScore, southScore, westScore, northScore, mahJong);
		p1.setTotalScore(p1.getTotalScore() + g.getPlayerByName(p1.getName()).getScore());
		p2.setTotalScore(p2.getTotalScore() + g.getPlayerByName(p2.getName()).getScore());
		p3.setTotalScore(p3.getTotalScore() + g.getPlayerByName(p3.getName()).getScore());
		p4.setTotalScore(p4.getTotalScore() + g.getPlayerByName(p4.getName()).getScore());
	}
	
	public Game turnWind(){
		try{
			Game g = gameList.getLast();
			return new Game(g.getNorthPlayer().getName(), g.getEastPlayer().getName(), g.getSouthPlayer().getName(), g.getWestPlayer().getName());
		}catch(Exception e){
			return new Game(p1.getName(), p2.getName(), p3.getName(), p4.getName());
		}
	}
}
