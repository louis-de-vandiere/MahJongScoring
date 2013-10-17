package structure;

import android.os.Parcel;
import android.os.Parcelable;


public class Player implements Parcelable{
	Wind wind ;
	int score;
	int scoreOwed;
	int scoreWon;
	int totalScore;
	int nbMahJong;
	String name;

	public Player(String name){
		this.name = name;
		score = 0;
		totalScore = 0;
		nbMahJong = 0;
		wind = Wind.NONE;
	}
	public Player(Wind wind) {
		this.wind = wind;
		//this.name = name.toLowerCase(Locale.getDefault());
		score = 0;
		totalScore = 0;
		nbMahJong = 0;
	}
	public Player(Wind wind, int score) {
		this.wind = wind;
		//this.name = name.toLowerCase(Locale.getDefault());
		this.score = score;
		totalScore = 0;
		nbMahJong = 0;
	}
	public Player(String name, Wind wind) {
		this.wind = wind;
		this.name = name;
		//this.name = name.toLowerCase(Locale.getDefault());
		score = 0;
		totalScore = 0;
		nbMahJong = 0;
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getNbMahJong() {
		return nbMahJong;
	}
	public void setNbMahJong(int nbMahJong) {
		this.nbMahJong = nbMahJong;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public int getScoreOwed() {
		return scoreOwed;
	}
	public void setScoreOwed(int scoreOwed) {
		this.scoreOwed = scoreOwed;
	}
	public int getScoreWon() {
		return scoreWon;
	}
	public void setScoreWon(int scoreWon) {
		this.scoreWon = scoreWon;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel p, int flags) {
		p.writeInt(score);
		p.writeInt(scoreOwed);
		p.writeInt(scoreWon);
		p.writeInt(wind.ordinal());
		p.writeString(name);
	}
	
	public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>()
			{
			    @Override
			    public Player createFromParcel(Parcel source)
			    {
			        return new Player(source);
			    }

			    @Override
			    public Player[] newArray(int size)
			    {
				return new Player[size];
			    }
			};

			public Player(Parcel in) {
				this.score = in.readInt();
				this.scoreOwed = in.readInt();
				this.scoreWon = in.readInt();
				this.wind = Wind.values()[in.readInt()];
				this.name = in.readString();
			}
}
