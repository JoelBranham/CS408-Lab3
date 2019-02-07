package com.example.jsu.lab3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.*;

public class CryptoLogicActivity extends AppCompatActivity {

    private ArrayList<String> secretWords;
    private ArrayList<Character> guessedLetters;
    private Random random;

    private int totalGuesses;
    private int correctGuesses;

    private String mysteryWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_logic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        secretWords = new ArrayList(Arrays.asList("APPLE", "BANANA", "CARROT", "WATERMELON", "ORANGE", "GRAPE"));
        guessedLetters = new ArrayList<Character>();

        random = new Random();
        selectMysteryWord();

        totalGuesses = correctGuesses = 0;
    }

    private void selectMysteryWord(){
        String word = secretWords.get(random.nextInt(secretWords.size()));
        mysteryWord = "";
        ArrayList<String> splitWord = new ArrayList(Arrays.asList(word.split("")));
        Collections.shuffle(splitWord);
        for (String c : splitWord)
            mysteryWord += c;
    }

    public void guessLetter(View v){
        TextView guessTextView = (TextView) findViewById(R.id.guessEditText);
        char guessedLetter = guessTextView.getText().charAt(0);

        if (!guessedLetters.contains(guessedLetter)){
            guessedLetters.add(guessedLetter);
        }

        for (char c: mysteryWord.toCharArray()){
            if (c == guessedLetter){
                correctGuesses++;
            }
        }
        totalGuesses++;

        if (correctGuesses == mysteryWord.length()){
            gameover();
        }

        updateTextViews();

    }

    private void gameover(){



        //happens at end
        selectMysteryWord();
    }

    private void updateTextViews(){

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crypto_logic, menu);
        return true;
    }

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
}
