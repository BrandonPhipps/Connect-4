package lab7out;

import java.io.Serializable;
import java.util.*;

public class HomeScreenData implements Serializable {
	private Integer lose;
	private Integer win;
	private Integer draw;
	private ArrayList<String> topTen;
	private String username;
	public Integer getLose() {
		return lose;
	}
	public void setLose(Integer lose) {
		this.lose = lose;
	}
	public Integer getWin() {
		return win;
	}
	public void setWin(Integer win) {
		this.win = win;
	}
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public ArrayList<String> getTopTen() {
		return topTen;
	}
	public void setTopTen(ArrayList<String> topTen) {
		for(String list: topTen) {
			this.topTen.add(list);	
		}
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	 
	 public HomeScreenData(String username, Integer win, Integer lose, Integer draw, ArrayList<String> topTen)
	 {
		 this.topTen = new ArrayList<String>();
		 setUsername(username);
		 setWin(win);
		 setLose(lose);
		 setDraw(draw);
		 setTopTen(topTen);
	 }
}
	