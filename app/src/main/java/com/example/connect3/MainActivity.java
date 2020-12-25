package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    // 0  is red
    // 1 is yellow
    // 2 empty
    int activePlayer = 0;
    boolean gameActive = true; // active until someOne has won or draw
    boolean someoneWon =false;
    String winner ="";
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = { {0,1,2} , {3,4,5} , {6,7,8} , {0,3,6} , {1,4,7},{2,5,8},{0,4,8} , {2,4,6} };
    public boolean checkIfDraw(){
        for(int i : gameState){
            if(i==2)
                return false;
        }
        return true;
    }
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                activePlayer = 1;
                counter.setImageResource(R.drawable.red);
            } else {
                activePlayer = 0;
                counter.setImageResource(R.drawable.yellow);
            }
            counter.animate().translationYBy(1500).rotationBy(3600).setDuration(300);
            checkForWin();
        }

    }
    private void checkForWin() {
        for (int[] winingPosition : winningPositions) {
            if (gameState[winingPosition[0]] == gameState[winingPosition[1]] && gameState[winingPosition[1]] == gameState[winingPosition[2]] && gameState[winingPosition[0]] != 2) {
                // all are equal and not empty
                gameActive = false;
                someoneWon =true;
                if (activePlayer == 1) {
                    winner = "Red ";
                } else {
                    winner = "Yellow ";
                }
            }
            else if( checkIfDraw() && !someoneWon  ){
                gameActive =false;
                winner = " DRAW ";

            }
            if( !gameActive ){
                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                TextView textViewWinner = (TextView) findViewById(R.id.textViewWinner);

                if( someoneWon ){
                    textViewWinner.setText( winner +  " Player Won ");
                }else{
                    textViewWinner.setText(" Draw !!");
                }
                textViewWinner.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
            }

        }
    }
    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView textViewWinner = (TextView) findViewById(R.id.textViewWinner);
        textViewWinner.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        Arrays.fill(gameState,2);
        activePlayer = 0;
        gameActive = true; // active until someOne has won or draw
        someoneWon =false;
        String winner ="";
//        GridLayout myGridView = (GridLayout) findViewById(R.id.gridLayout);

        androidx.gridlayout.widget.GridLayout myGridView = findViewById(R.id.gridLayout);
        for(int i=0; i<myGridView.getChildCount(); i++) {
            ImageView child = (ImageView) myGridView.getChildAt(i);
            child.setImageDrawable(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}