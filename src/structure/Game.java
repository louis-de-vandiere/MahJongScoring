package structure;

import android.os.Parcel;
import android.os.Parcelable;


public class Game implements Parcelable{
	Player east;
	Player south;
	Player west;
	Player north;
	Wind mahJong;
	
	public Game(int east, int south, int west, int north) {
		this.east = new Player(Wind.EAST, east);
		this.south = new Player(Wind.SOUTH, south);
		this.west = new Player(Wind.WEST, west);
		this.north = new Player(Wind.NORTH, north);
		this.mahJong = Wind.NONE;  
	}
	
	public Game(String east, String south, String west, String north){
		this.east = new Player(east, Wind.EAST);
		this.south = new Player(south, Wind.SOUTH);
		this.west = new Player(west, Wind.WEST);
		this.north = new Player(north, Wind.NORTH);
		this.mahJong = Wind.NONE;
	}

	public void score(Wind mahJong){
		this.mahJong = mahJong;
		setEastScore();
		setSouthScore();
		setWestScore();
		setNorthScore();
		
		east.setScore(east.getScoreWon() - east.getScoreOwed());
		south.setScore(south.getScoreWon() - south.getScoreOwed());
		west.setScore(west.getScoreWon() - west.getScoreOwed());
		north.setScore(north.getScoreWon() - north.getScoreOwed());
	}
	
	public void score(int eastScore, int southScore, int westScore, int northScore,Wind mahJong){
		this.mahJong = mahJong;
		east.setScore(eastScore);
		south.setScore(southScore);
		west.setScore(westScore);
		north.setScore(northScore);
		setEastScore();
		setSouthScore();
		setWestScore();
		setNorthScore();
		
		east.setScore(east.getScoreWon() - east.getScoreOwed());
		south.setScore(south.getScoreWon() - south.getScoreOwed());
		west.setScore(west.getScoreWon() - west.getScoreOwed());
		north.setScore(north.getScoreWon() - north.getScoreOwed());
	}
	public Player getPlayerByName(String name){
		if(east.getName().equals(name)){
			return east;
		}else if(south.getName().equals(name)){
			return south;
		}else if(west.getName().equals(name)){
			return west;
		}else{
			return north;
		}
	}
	public Wind getMahJong(){
		return mahJong;
	}
	
	public Player getEastPlayer(){
		return east;
	}
	
	public Player getSouthPlayer(){
		return south;
	}
	
	public Player getWestPlayer(){
		return west;
	}
	
	public Player getNorthPlayer(){
		return north;
	}
	
	public void setEastScore(){
		int eastScore = east.getScore();
		int southScore = south.getScore();
		int westScore = west.getScore();
		int northScore = north.getScore();
		int scoreOwed=0;
		int scoreWon=0;
		
		switch(mahJong){
			case EAST :
				scoreWon += 6*eastScore;
				break;
			case SOUTH :
				scoreOwed = 2*southScore;
				if(eastScore < westScore){
					scoreOwed += 2*(westScore - eastScore);
				}else{
					scoreWon += 2*(eastScore - westScore);
				}
				if(eastScore < northScore){
					scoreOwed += 2*(northScore - eastScore);
				}else{
					scoreWon += 2*(eastScore - northScore);
				}
				break;
			case WEST :
				scoreOwed = 2*westScore;
				if(eastScore < southScore){
					scoreOwed += 2*(southScore - eastScore);
				}else{
					scoreWon += 2*(eastScore - southScore);
				}
				if(eastScore < northScore){
					scoreOwed += 2*(northScore - eastScore);
				}else{
					scoreWon += 2*(eastScore - northScore);
				}
				break;
			case NORTH :
				scoreOwed = 2*northScore;
				if(eastScore < southScore){
					scoreOwed += 2*(southScore - eastScore);
				}else{
					scoreWon += 2*(eastScore - southScore);
				}
				if(eastScore < westScore){
					scoreOwed += 2*(westScore - eastScore);
				}else{
					scoreWon += 2*(eastScore - westScore);
				}
				break;
			default :
				if(eastScore < southScore){
					scoreOwed += 2*(southScore - eastScore);
				}else{
					scoreWon += 2*(eastScore - southScore);
				}
				if(eastScore < westScore){
					scoreOwed += 2*(westScore - eastScore);
				}else{
					scoreWon += 2*(eastScore - westScore);
				}
				if(eastScore < northScore){
					scoreOwed += 2*(northScore - eastScore);
				}else{
					scoreWon += 2*(eastScore - northScore);
				}
		}
		System.out.println(scoreOwed + " " + scoreWon);
		east.setScoreOwed(scoreOwed);
		east.setScoreWon(scoreWon);
	}
	public void setSouthScore(){
		int eastScore = east.getScore();
		int southScore = south.getScore();
		int westScore = west.getScore();
		int northScore = north.getScore();
		int scoreOwed=0;
		int scoreWon=0;
		
		switch(mahJong){
			case EAST :
				scoreOwed += 2*eastScore;
				if(southScore < westScore){
					scoreOwed += westScore - southScore;
				}else{
					scoreWon += southScore - westScore;
				}
				if(southScore < northScore){
					scoreOwed += northScore - southScore;
				}else{
					scoreWon += southScore - northScore;
				}
				break;
			case SOUTH :
				scoreWon += 4*southScore;
				break;
			case WEST :
				scoreOwed = westScore;
				if(southScore < eastScore){
					scoreOwed += 2*(eastScore - southScore);
				}else{
					scoreWon += 2*(southScore - eastScore);
				}
				if(southScore < northScore){
					scoreOwed += northScore - southScore;
				}else{
					scoreWon += southScore - northScore;
				}
				break;
			case NORTH :
				scoreOwed = northScore;
				if(southScore < eastScore){
					scoreOwed += 2*(eastScore - southScore);
				}else{
					scoreWon += 2*(southScore - eastScore);
				}
				if(southScore < westScore){
					scoreOwed += westScore - southScore;
				}else{
					scoreWon += southScore - westScore;
				}
				break;
			default :
				if(southScore < eastScore){
					scoreOwed += 2*(eastScore - southScore);
				}else{
					scoreWon += 2*(southScore - eastScore);
				}
				if(southScore < westScore){
					scoreOwed += westScore - southScore;
				}else{
					scoreWon += southScore - westScore;
				}
				if(southScore < northScore){
					scoreOwed += northScore - southScore;
				}else{
					scoreWon += southScore - northScore;
				}
		}	
		south.setScoreOwed(scoreOwed);
		south.setScoreWon(scoreWon);
	}
	public void setWestScore(){
		int eastScore = east.getScore();
		int southScore = south.getScore();
		int westScore = west.getScore();
		int northScore = north.getScore();
		int scoreOwed=0;
		int scoreWon=0;
		
		switch(mahJong){
			case EAST :
				scoreOwed += 2*eastScore;
				if(westScore < southScore){
					scoreOwed += southScore - westScore;
				}else{
					scoreWon += westScore - southScore;
				}
				if(westScore < northScore){
					scoreOwed += northScore - westScore;
				}else{
					scoreWon += westScore - northScore;
				}
				break;
			case SOUTH :
				scoreOwed = southScore;
				if(westScore < eastScore){
					scoreOwed += 2*(eastScore - westScore);
				}else{
					scoreWon += 2*(westScore - eastScore);
				}
				if(westScore < northScore){
					scoreOwed += northScore - westScore;
				}else{
					scoreWon += westScore - northScore;
				}
				break;
			case WEST :
				scoreWon += 4*westScore;
				break;
			case NORTH :
				scoreOwed = northScore;
				if(westScore < eastScore){
					scoreOwed += 2*(eastScore - westScore);
				}else{
					scoreWon += 2*(westScore - eastScore);
				}
				if(westScore < southScore){
					scoreOwed += southScore - westScore;
				}else{
					scoreWon += westScore - southScore;
				}
				break;
			default :
				if(westScore < eastScore){
					scoreOwed += 2*(eastScore - westScore);
				}else{
					scoreWon += 2*(westScore - eastScore);
				}
				if(westScore < southScore){
					scoreOwed += southScore - westScore;
				}else{
					scoreWon += westScore - southScore;
				}
				if(westScore < northScore){
					scoreOwed += northScore - westScore;
				}else{
					scoreWon += westScore - northScore;
				}
		}	
		west.setScoreOwed(scoreOwed);
		west.setScoreWon(scoreWon);
	}
	public void setNorthScore(){
		int eastScore = east.getScore();
		int southScore = south.getScore();
		int westScore = west.getScore();
		int northScore = north.getScore();
		int scoreOwed=0;
		int scoreWon=0;
		
		switch(mahJong){
			case EAST :
				scoreOwed += 2*eastScore;
				if(northScore < southScore){
					scoreOwed += southScore - northScore;
				}else{
					scoreWon += northScore - southScore;
				}
				if(northScore < westScore){
					scoreOwed += westScore - northScore;
				}else{
					scoreWon += northScore - westScore;
				}
				break;
			case SOUTH :
				scoreOwed = southScore;
				if(northScore < eastScore){
					scoreOwed += 2*(eastScore - northScore);
				}else{
					scoreWon += 2*(northScore - eastScore);
				}
				if(northScore < westScore){
					scoreOwed += westScore - northScore;
				}else{
					scoreWon += northScore - westScore;
				}
				break;
			case WEST :
				scoreOwed = westScore;
				if(northScore < eastScore){
					scoreOwed += 2*(eastScore - northScore);
				}else{
					scoreWon += 2*(northScore - eastScore);
				}
				if(northScore < southScore){
					scoreOwed += southScore - northScore;
				}else{
					scoreWon += northScore - southScore;
				}
				break;
			case NORTH :
				scoreWon += 4*northScore;
				break;
			default :
				if(northScore < eastScore){
					scoreOwed += 2*(eastScore - northScore);
				}else{
					scoreWon += 2*(northScore - eastScore);
				}
				if(northScore < southScore){
					scoreOwed += southScore - northScore;
				}else{
					scoreWon += northScore - southScore;
				}
				if(northScore < westScore){
					scoreOwed += westScore - northScore;
				}else{
					scoreWon += northScore - westScore;
				}
		}	
		north.setScoreOwed(scoreOwed);
		north.setScoreWon(scoreWon);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(east, 0);
		dest.writeParcelable(south, 1);
		dest.writeParcelable(west, 2);
		dest.writeParcelable(north, 3);
		dest.writeInt(mahJong.ordinal());
	}
	
	public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>()
			{
			    @Override
			    public Game createFromParcel(Parcel source)
			    {
			        return new Game(source);
			    }

			    @Override
			    public Game[] newArray(int size)
			    {
				return new Game[size];
			    }
			};

			public Game(Parcel in) {
				this.east = in.readParcelable(Player.class.getClassLoader());
				this.south = in.readParcelable(Player.class.getClassLoader());
				this.west = in.readParcelable(Player.class.getClassLoader());
				this.north = in.readParcelable(Player.class.getClassLoader());
				this.mahJong = Wind.values()[in.readInt()];
			}
}
