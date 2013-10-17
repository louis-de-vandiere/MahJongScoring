package mahjongscoring.application;

import structure.Game;
import structure.Wind;
import mahjongscoring.application.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Score extends Activity {
	//Le vent mahjong de la partie
	Wind mahJong = Wind.NONE;
	//La partie
	Game g;
	//Le layout d'une partie
	LinearLayout score;
    //Bouton sur le score
    Button scoreButton;
    //nom des joueurs
    TextView eastName;
    TextView southName;
    TextView westName;
    TextView northName;
    //EditText sur les scores des vents
	EditText eastScore;
    EditText southScore;
    EditText westScore;
    EditText northScore;
    //EditText sur le score totale
    TextView eastTotal;
    TextView southTotal;
    TextView westTotal;
    TextView northTotal;
    //EditText sur le score gagné
    TextView eastWon;
    TextView southWon;
    TextView westWon;
    TextView northWon;
    //EditText sur le score dû
    TextView eastOwed;
    TextView southOwed;
    TextView westOwed;
    TextView northOwed;
    //CheckBox mahjong
	CheckBox eastMahJong;
    CheckBox southMahJong;
    CheckBox westMahJong;
    CheckBox northMahJong;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score); 
        instantiateComponents();
        g = getIntent().getExtras().getParcelable("game");
        setName(g);
        onCheckListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_score, menu);
        return true;
    }
    public void setName(Game g){
    	eastMahJong.setChecked(false);
    	southMahJong.setChecked(false);
		westMahJong.setChecked(false);
		northMahJong.setChecked(false);
		
		eastName.setText(g.getEastPlayer().getName());
		southName.setText(g.getSouthPlayer().getName());
		westName.setText(g.getWestPlayer().getName());
		northName.setText(g.getNorthPlayer().getName());
    }
    
    public void instantiateComponents(){
    	score = (LinearLayout)findViewById(R.id.scoreView);
    	scoreButton = (Button)findViewById(R.id.giveScore);
    	//nom des joueurs
    	eastName = (TextView)findViewById(R.id.eastPlayer);
    	southName = (TextView)findViewById(R.id.southPlayer);
    	westName = (TextView)findViewById(R.id.westPlayer);
    	northName = (TextView)findViewById(R.id.northPlayer);
    	//EditText sur les scores des vents
    	eastScore = (EditText)findViewById(R.id.eastScore);
        southScore = (EditText)findViewById(R.id.southScore);
        westScore = (EditText)findViewById(R.id.westScore);
        northScore = (EditText)findViewById(R.id.northScore);
        //EditText sur le score totale
        eastTotal = (TextView)findViewById(R.id.eastScoreTotal);
        southTotal = (TextView)findViewById(R.id.southScoreTotal);
        westTotal = (TextView)findViewById(R.id.westScoreTotal);
        northTotal = (TextView)findViewById(R.id.northScoreTotal);
        //EditText sur le score gagné
        eastWon = (TextView)findViewById(R.id.eastScoreWon);
        southWon = (TextView)findViewById(R.id.southScoreWon);
        westWon = (TextView)findViewById(R.id.westScoreWon);
        northWon = (TextView)findViewById(R.id.northScoreWon);
        //EditText sur le score dû
        eastOwed = (TextView)findViewById(R.id.eastScoreOwed);
        southOwed = (TextView)findViewById(R.id.southScoreOwed);
        westOwed= (TextView)findViewById(R.id.westScoreOwed);
        northOwed = (TextView)findViewById(R.id.northScoreOwed);
        //CheckBox mahjong
    	eastMahJong = (CheckBox)findViewById(R.id.eastMahJong);
        southMahJong = (CheckBox)findViewById(R.id.southMahJong);
        westMahJong = (CheckBox)findViewById(R.id.westMahJong);
        northMahJong = (CheckBox)findViewById(R.id.northMahJong);
    }
    
    public void okButton(View v){
    	Intent intent = new Intent(Score.this, MahJong.class); 
    	intent.putExtra("gameScore", g);
    	System.out.println(g.getEastPlayer().getWind());
    	setResult(1, intent);
    	finish();
    }
    //Le bouton Give Score ! donne le score suivant les données entrées
    public void giveScoreButton(View v){
        try{     	
			g.score(Integer.valueOf(eastScore.getText().toString()),Integer.valueOf(southScore.getText().toString()),
					Integer.valueOf(westScore.getText().toString()),Integer.valueOf(northScore.getText().toString()),mahJong);
			
			eastTotal.setText(String.valueOf(g.getEastPlayer().getScore()));
			southTotal.setText(String.valueOf(g.getSouthPlayer().getScore()));
			westTotal.setText(String.valueOf(g.getWestPlayer().getScore()));
			northTotal.setText(String.valueOf(g.getNorthPlayer().getScore()));
			
			eastWon.setText(String.valueOf(g.getEastPlayer().getScoreWon()));
			southWon.setText(String.valueOf(g.getSouthPlayer().getScoreWon()));
			westWon.setText(String.valueOf(g.getWestPlayer().getScoreWon()));
			northWon.setText(String.valueOf(g.getNorthPlayer().getScoreWon()));
			
			eastOwed.setText(String.valueOf(g.getEastPlayer().getScoreOwed()));
			southOwed.setText(String.valueOf(g.getSouthPlayer().getScoreOwed()));
			westOwed.setText(String.valueOf(g.getWestPlayer().getScoreOwed()));
			northOwed.setText(String.valueOf(g.getNorthPlayer().getScoreOwed()));
        }catch(Exception e){
        	System.out.println(e);
        }
    }
    //Ecouteur sur les checkbox MahJong
    public void onCheckListener(){
        
        eastMahJong.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v)
        	{
        		if(eastMahJong.isChecked()){
        			southMahJong.setChecked(false);
        			westMahJong.setChecked(false);
        			northMahJong.setChecked(false);
        			mahJong = Wind.EAST;
        		}else{
        			mahJong = Wind.NONE;
        		}
        	}
        });
        southMahJong.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v)
        	{
        		if(southMahJong.isChecked()){
        			eastMahJong.setChecked(false);
        			westMahJong.setChecked(false);
        			northMahJong.setChecked(false);
        			mahJong = Wind.SOUTH;
        		}else{
        			mahJong = Wind.NONE;
        		}
        	}
        });
        westMahJong.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v)
        	{
        		if(westMahJong.isChecked()){
        			eastMahJong.setChecked(false);
        			southMahJong.setChecked(false);
        			northMahJong.setChecked(false);
        			mahJong = Wind.WEST;
        		}else{
        			mahJong = Wind.NONE;
        		}
        	}
        });
        northMahJong.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v)
        	{
        		if(northMahJong.isChecked()){
        			eastMahJong.setChecked(false);
        			southMahJong.setChecked(false);
        			westMahJong.setChecked(false);
        			mahJong = Wind.NORTH;
        		}else{
        			mahJong = Wind.NONE;
        		}
        	}
        });
    }
}
