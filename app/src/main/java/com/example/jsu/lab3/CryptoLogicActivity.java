package com.example.jsu.lab3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.*;

public class CryptoLogicActivity extends AppCompatActivity {

    private ArrayList<String> secretWords;
    private ArrayList<Character> correctGuessedLetters;
    private Random random;

    private int totalGuesses;

    private String mysteryWord;
    private String scrambledWord;

    private boolean gameover;

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

        secretWords = new ArrayList<String>(Arrays.asList("APPLE", "BANANA", "CARROT", "WATERMELON", "ORANGE", "GRAPE"));
        random = new Random();
        reset();
    }

    private void selectMysteryWord(){
        mysteryWord = secretWords.get(random.nextInt(secretWords.size()));
        scrambledWord = "";
        ArrayList<String> splitWord = new ArrayList<String>(Arrays.asList(mysteryWord.split("")));
        Collections.shuffle(splitWord);
        for (String c : splitWord)
            scrambledWord += c;
    }

    public void guessLetter(View v){

        if (gameover){
            reset();
        }
        else{
            TextView guessTextView = (TextView) findViewById(R.id.guessEditText);
            if (guessTextView.getText().length() > 0){
                char guessedLetter = guessTextView.getText().charAt(0);
                if (mysteryWord.charAt(correctGuessedLetters.size()) == guessedLetter){
                    correctGuessedLetters.add(guessedLetter);
                }

                totalGuesses++;

                if (correctGuessedLetters.size() == mysteryWord.length()){
                    gameover = true;

                    TextView mysteryWordTextView = findViewById(R.id.mysteryWordTextView);
                    mysteryWordTextView.setText(getString(R.string.mystery_word, mysteryWord));

                    TextView numberIncorrectTextView = findViewById(R.id.incorrectTextView);
                    numberIncorrectTextView.setText(getString(R.string.incorrect_guesses, totalGuesses - correctGuessedLetters.size()));

                    Button guessButton = findViewById(R.id.guessButton);
                    guessButton.setText(R.string.newGame);
                }

                updateCorrectGuessedTextView();
                clearGuessEditText();
            }
        }
    }

    private void reset(){
        gameover = false;
        totalGuesses = 0;
        correctGuessedLetters = new ArrayList<Character>();

        selectMysteryWord();

        updateScrambledWordTextView();
        updateCorrectGuessedTextView();
        clearMysteryWordTextView();
        clearIncorrectTextView();
        clearGuessEditText();
        resetGuessButtonText();
    }

    private void resetGuessButtonText(){
        Button guessButton = findViewById(R.id.guessButton);
        guessButton.setText(R.string.guess);
    }

    private void updateScrambledWordTextView(){
        TextView scrambledWordTextView = findViewById(R.id.scrambledWordTextView);
        scrambledWordTextView.setText(getString(R.string.scrambled_word, scrambledWord));
    }

    private void updateCorrectGuessedTextView(){
        TextView correctGuessedTextView = findViewById(R.id.correctGuessedTextView);
        String s = "";
        for (char c: correctGuessedLetters){
            s += c + " ";
        }
        correctGuessedTextView.setText(getString(R.string.correct_letters, s));
    }

    private void clearMysteryWordTextView(){
        TextView mysteryWordTextView = findViewById(R.id.mysteryWordTextView);
        mysteryWordTextView.setText("");
    }

    private void clearIncorrectTextView(){
        TextView numberIncorrectTextView = findViewById(R.id.incorrectTextView);
        numberIncorrectTextView.setText("");
    }

    private void clearGuessEditText(){
        TextView guessEditText = findViewById(R.id.guessEditText);
        guessEditText.setText("");
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
