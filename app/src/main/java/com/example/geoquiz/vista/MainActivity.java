package com.example.geoquiz.vista;

import com.example.geoquiz.R;
import com.example.geoquiz.controlador.Question;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Integer mRandomNumber;
private Question []mQuestionBank;
    private int mCurrentIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarQuestionBank();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mTrueButton = (Button) findViewById(R.id.btn_true);
        clickTrueButton();
        mFalseButton =(Button) findViewById(R.id.btn_false);
        clickFalseButton();
        mNextButton= (Button) findViewById(R.id.btn_next);
        clickNextButton();
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();
    }
    public void clickTrueButton(){
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkAnswer(true);
            }
        });
    }
    public void clickFalseButton(){
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
    }
    public void clickNextButton(){
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion();
            }
        });
    }
    public void inicializarQuestionBank(){
        mQuestionBank = new Question[] {
                new Question(R.string.question_oceans, true),
                new Question(R.string.question_mideast, false),
                new Question(R.string.question_africa, false),
                new Question(R.string.question_americas, true),
                new Question(R.string.question_asia, true),
                new Question(R.string.question_europe, true),
                new Question(R.string.question_antarctica, true),
                new Question(R.string.question_australia, true),
                new Question(R.string.question_space, true),
                new Question(R.string.question_science, true),
                new Question(R.string.question_math, true),
                new Question(R.string.question_history, true),
                new Question(R.string.question_geography, true),
                new Question(R.string.question_politics, true),
                new Question(R.string.question_technology, true),
                new Question(R.string.question_biology, true),
                new Question(R.string.question_physics, true),
                new Question(R.string.question_chemistry, true),
                new Question(R.string.question_literature, true),
                new Question(R.string.question_art, true),
                new Question(R.string.question_music, true),
                new Question(R.string.question_sports, true),
                new Question(R.string.question_movies, true),
                new Question(R.string.question_animals, true),
                new Question(R.string.question_languages, true),
                new Question(R.string.question_fiction, false),
                new Question(R.string.question_inventions, false),
                new Question(R.string.question_food, false),
                new Question(R.string.question_planets, false),
                new Question(R.string.question_history2, false),
                new Question(R.string.question_languages2, false),
                new Question(R.string.question_math2, false),
                new Question(R.string.question_animals2, false),
                new Question(R.string.question_music2, false),
                new Question(R.string.question_sports2, false)
        };
    }
    public void updateQuestion(){
        mRandomNumber = genNewRandonNumber();
        mQuestionTextView.setText(mQuestionBank[mRandomNumber].getmRestId());
    }
    public int genNewRandonNumber(){
        if(mRandomNumber == null) mRandomNumber = 0;
        Random genRandom = new Random();
        int random = genRandom.nextInt(mQuestionBank.length);
        while(random == mRandomNumber){
            random = genRandom.nextInt(mQuestionBank.length);
        }
        return random;
    }
    public boolean verifyAnswer(boolean answer){
        return answer == mQuestionBank[mRandomNumber].isAnswerTrue();
    }
    public void checkAnswer(boolean answer){
        int messageResId;
        if(verifyAnswer(answer)){
            messageResId = R.string.correct_toast;
        }else{
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(MainActivity.this, messageResId, Toast.LENGTH_SHORT).show();
    }
}