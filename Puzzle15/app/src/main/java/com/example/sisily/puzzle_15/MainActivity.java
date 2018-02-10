package com.example.sisily.puzzle_15;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewGroup mainView;

    /** The game board. */
    private Board board;

    /** The board view that generates the tiles and lines using 2-D graphics. */
    private BoardView boardView;

    /** Text view to show the user the number of movements. */
    private TextView moves;

    /** The board size. Default value is an 4x4 game. */
   // private int boardSize = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        mainView = (ViewGroup) findViewById(R.id.mainLayout);
        moves = (TextView) findViewById(R.id.moves);
        moves.setTextColor(Color.BLUE);
        moves.setTextSize(20);
        this.newGame();

       /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       Button yourBtn = (Button)findViewById(R.id.block1);
        yourBtn.setOnClickListener(this);
        yourBtn = (Button)findViewById(R.id.block2);
        yourBtn.setOnClickListener(this);
        yourBtn = (Button)findViewById(R.id.block3);
        yourBtn.setOnClickListener(this);
        yourBtn = (Button)findViewById(R.id.block4);
        yourBtn.setOnClickListener(this);
        yourBtn = (Button)findViewById(R.id.block5);
        yourBtn.setOnClickListener(this);
        yourBtn = (Button)findViewById(R.id.block6);
        yourBtn.setOnClickListener(this);
        yourBtn = (Button)findViewById(R.id.block7);
        yourBtn.setOnClickListener(this);
        yourBtn = (Button)findViewById(R.id.block8);
        yourBtn.setOnClickListener(this);
        yourBtn = (Button)findViewById(R.id.block9);
        yourBtn.setOnClickListener(this);
        yourBtn = (Button)findViewById(R.id.block10);
        yourBtn.setOnClickListener(this);
        yourBtn = (Button)findViewById(R.id.block11);
        yourBtn.setOnClickListener(this);
        yourBtn = (Button)findViewById(R.id.block12);
        yourBtn.setOnClickListener(this);
        yourBtn = (Button)findViewById(R.id.block13);
        yourBtn.setOnClickListener(this);
        yourBtn = (Button)findViewById(R.id.block14);
        yourBtn.setOnClickListener(this);
        yourBtn = (Button)findViewById(R.id.block15);
        yourBtn.setOnClickListener(this);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void newGame() {
        this.board = new Board();
        this.board.addBoardChangeListener(boardChangeListener);
        this.board.rearrange();
        this.mainView.removeView(boardView);
        this.boardView = new BoardView(this, board);
        this.mainView.addView(boardView);
        this.moves.setText("Number of movements: 0");
    }

    private Board.BoardChangeListener boardChangeListener = new Board.BoardChangeListener() {
        public void tileSlid(Position from, Position to, int numOfMoves) {
            moves.setText("Number of movements: "
                    + Integer.toString(numOfMoves));
        }

        public void solved(int numOfMoves) {
            moves.setText("Solved in " + Integer.toString(numOfMoves)
                    + " moves!");
            Toast.makeText(getApplicationContext(), "You won!",
                    Toast.LENGTH_LONG).show();
        }
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        String text_inButton;
        text_inButton = (String) ( (TextView) v).getText();
        if (text_inButton.equals("solve")) {
            //board.rearrange();
            System.out.println("Space pressed");
        }

        else {
            int number = Integer.parseInt(text_inButton);
            System.out.println("Block " + number + " pressed");
        }
        ((Button) v ).setTextColor(Color.parseColor("#303F9F"));
    }

    public void suffleTile (View v){
        String text_inButton;
        text_inButton = (String) ( (TextView) v).getText();

        if (text_inButton.equals("shuffle")) System.out.println("Shuffle pressed");

        ((Button) v ).setTextColor(Color.parseColor("#303F9F"));

        board.rearrange();
        moves.setText("Number of movements: 0");
        boardView.invalidate();

    }

    public void solveTile (View v){
        String text_inButton;
        text_inButton = (String) ( (TextView) v).getText();

        if (text_inButton.equals("solve")) System.out.println("Solvehere pressed");

        ((Button) v ).setTextColor(Color.parseColor("#303F9F"));
       // board.solveAstar();
        //board.solveManhattan();
       board.solveDFS();
        System.out.println("Solveeeee pressed");
    }

}