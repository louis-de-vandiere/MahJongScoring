package mahjongscoring.application;

import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import structure.*;

public class MahJong extends Activity {
	TableLayout tableScore;
	Match match;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mah_jong);
		tableScore = (TableLayout)findViewById(R.id.tableScore);
		match = new Match("joueur1", "joueur2", "joueur3", "joueur4");
		dialogSetName();
	}
	public void newGame(View v){
		match.newGame();
		Intent intent = new Intent(MahJong.this, Score.class);
		intent.putExtra("game", match.getGameList().getLast());
		startActivityForResult(intent, 1);
    }
	public void setTotalView(View v){
		TableRow tr = new TableRow(this);
		TextView tv = setCell("Total");
		tr.addView(tv);
		//Joueur1
		tv = setCell(Integer.toString(match.getP1().getTotalScore()));
		tr.addView(tv);
		//Joueur2
		tv = setCell(Integer.toString(match.getP2().getTotalScore()));
		tr.addView(tv);
		//Joueur3
		tv = setCell(Integer.toString(match.getP3().getTotalScore()));
		tr.addView(tv);
		//Joueur4
		tv = setCell(Integer.toString(match.getP4().getTotalScore()));
		tr.addView(tv);
		
		tableScore.addView(tr);
	}
	public void removeTotalView(View v){
		TableRow tr = (TableRow)tableScore.getChildAt(tableScore.getChildCount()-1);
		TextView tv = (TextView)tr.getChildAt(0);
		
		if(tv.getText().equals("Total")){
			tableScore.removeView(tr);
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mah_jong, menu);
		return true;
	}
	
	public void setName(String p1, String p2, String p3, String p4){
		match.setName(p1, p2, p3, p4);
		TextView tv = (TextView)findViewById(R.id.player1);
		tv.setText(p1);
		
		tv = (TextView)findViewById(R.id.player2);
		tv.setText(p2);
		
		tv = (TextView)findViewById(R.id.player3);
		tv.setText(p3);
		
		tv = (TextView)findViewById(R.id.player4);
		tv.setText(p4);
	}
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(resultCode == 1){
			TableRow tr = new TableRow(this);
			Game g =  data.getExtras().getParcelable("gameScore");
			match.getGameList().removeLast();
			match.getGameList().add(g);
			
			removeTotalView(tableScore);
			
			TextView tv = setCell(Integer.toString(match.getGameList().size()));
			tr.addView(tv);
			//Joueur1
			Player P1 = g.getPlayerByName(match.getP1().getName());
			tv = setScoreCellPlayer(P1, P1.getWind().equals(g.getMahJong()));
			tr.addView(tv);
			//Joueur2
			Player P2 = g.getPlayerByName(match.getP2().getName());
			tv = setScoreCellPlayer(P2, P2.getWind().equals(g.getMahJong()));
			tr.addView(tv);
			//Joueur3
			Player P3 = g.getPlayerByName(match.getP3().getName());
			tv = setScoreCellPlayer(P3, P3.getWind().equals(g.getMahJong()));
			tr.addView(tv);
			//Joueur4
			Player P4 = g.getPlayerByName(match.getP4().getName());
			tv = setScoreCellPlayer(P4, P4.getWind().equals(g.getMahJong()));
			tr.addView(tv);
			
			tableScore.addView(tr);
			match.score();
			setTotalView(tableScore);
		}else{
			match.getGameList().removeLast();
		}
	}
	
	public TextView setScoreCellPlayer(Player p, boolean mahjong){
		TextView v;
		if(mahjong){
			v = setCell(p.getScore() + "(M)");			
		}else{
			v = setCell(Integer.toString(p.getScore()));
		}
		switch(p.getWind()){
			case EAST :
				v.setTextColor(Color.RED);
				break;
			case SOUTH :
				v.setTextColor(Color.BLUE);
				break;
			case WEST :
				v.setTextColor(Color.GREEN);
				break;
			default :
				v.setTextColor(Color.YELLOW);
				break;
		}				
		return v;
	}
	
	public TextView setCell(String text){
		TextView v = new TextView(this);
		v.setBackground(getResources().getDrawable(R.drawable.cell_shape));
		v.setGravity(Gravity.CENTER);
		v.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,1f));
		v.setText(text);
		return v;
	}
	
	public void dialogSetName(){
		LayoutInflater inflater = getLayoutInflater();
		final View dialoglayout = inflater.inflate(R.layout.name_ayout, (ViewGroup) getCurrentFocus());
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setView(dialoglayout);
		// Set an EditText view to get user input 
	
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  String player1name = ((EditText)dialoglayout.findViewById(R.id.player1nameIn)).getText().toString();
		  String player2name = ((EditText)dialoglayout.findViewById(R.id.player2nameIn)).getText().toString();
		  String player3name = ((EditText)dialoglayout.findViewById(R.id.player3nameIn)).getText().toString();
		  String player4name = ((EditText)dialoglayout.findViewById(R.id.player4nameIn)).getText().toString();
		  setName(player1name,player2name,player3name, player4name);
		  }
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
		  }
		});

		alert.show();
	}
}
