package lab7out;

import java.io.Serializable;

public class HomeScreenData implements Serializable {
	private int lose;
	private int win;
	private int draw;
	private String topTen[];
	private String username;
	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public String[] getTopTen() {
		return topTen;
	}
	public void setTopTen(String[] topTen) {
		this.topTen = topTen;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	 
	 public HomeScreenData(String username, int win, int lose, int draw, String[] topTen)
	 {
		 setUsername(username);
		 setWin(win);
		 setLose(lose);
		 setDraw(draw);
		 setTopTen(topTen);
	 }
	
	

}
