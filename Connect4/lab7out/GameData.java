
package lab7out;

import java.io.Serializable;
import java.util.*;

public class GameData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private ArrayList<ArrayList<Integer>> board;
	private String outcome;
	private boolean done;
	private String info;
	private int placement;


/*
	public ArrayList<ArrayList<Integer>> getBoard(){
		return board;
	}
	*/
	
	public String getOutcome(){
		return outcome;
	}
	
	public boolean getDone(){
		return done;
	}
	public String info(){
		return info;
	}
	public int getPlacement(){
		return placement;
	}


/*	public void setBoard(ArrayList<ArrayList<Integer>> board){
		for(ArrayList<Integer> list: board) {
			ArrayList<Integer> holder = new ArrayList<Integer>();	
			for(Integer checker: list) {
				holder.add((Integer) checker); 
			}
			this.board.add((ArrayList<Integer>) holder.clone());
			holder.clear();
		}
	}
*/
	
	public void setOutcome(String outcome){
		this.outcome = outcome;
	}

	public void setInfo(String info){
		this.info = info;
	}
	public void setPlacement(int placement){
		this.placement = placement;
	}

	public GameData(int placement,boolean done){//String outcome,  String info,

		//setBoard(board);
/*		setOutcome(outcome);

		setInfo(info);*/
		this.done = done;
		setPlacement(placement);

	}

}
