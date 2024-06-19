package com.example.geoquiz;

import com.example.geoquiz.pojo.Question;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "INDEX";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Stack<Integer> mStackIndex;
    private Button mPrevButton;
    private Question[] mQuestionBank;
    public static final String TAG = "QuizActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inicializarWidGetsId();
        inicializarQuestionBank();
        setListenerPrevButton();
        setListenerNextButton();
        setListenerTextView();
        setListenerFalseButton();
        setListenerTrueButton();
        if(savedInstanceState != null){
            ArrayList<Integer> listaRecuperada = savedInstanceState.getIntegerArrayList(KEY_INDEX);
            if (listaRecuperada != null) {
                mStackIndex.addAll(listaRecuperada);
            }
            setTextViewQuestionOnSave();
        }else{
            setCurrentIndex();
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        ArrayList<Integer> listaIndex = new ArrayList<Integer>(mStackIndex);
        savedInstanceState.putIntegerArrayList(KEY_INDEX, listaIndex);
    }

    public void inicializarQuestionBank() {
        mQuestionBank = new Question[]{
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

    public void inicializarWidGetsId(){
        mPrevButton = findViewById(R.id.btn_prev);
        mTrueButton = findViewById(R.id.btn_true);
        mFalseButton = findViewById(R.id.btn_false);
        mNextButton = findViewById(R.id.btn_next);
        mQuestionTextView = findViewById(R.id.question_text_view);
        mStackIndex = new Stack<>();
    }
    public void setListenerPrevButton(){
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLastIndex();
            }
        });
    }
    public void setListenerNextButton(){
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentIndex();
            }
        });
    }
    public void setListenerTextView(){
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentIndex();
            }
        });
    }
    public void setListenerTrueButton(){
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCorrectAnswer(true);
            }
        });
    }
    public void setListenerFalseButton(){
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCorrectAnswer(false);
            }
        });
    }
    public void setCurrentIndex(){
       Random randomNumber = new Random();
       int number = randomNumber.nextInt(mQuestionBank.length);
       mQuestionTextView.setText(mQuestionBank[number].getmRestId());
       mStackIndex.push(number);
    }
    public void setLastIndex(){
        if(mStackIndex.size() >= 2){
            mStackIndex.pop();
            mQuestionTextView.setText(mQuestionBank[mStackIndex.peek()].getmRestId());
        }else{
            Toast.makeText(MainActivity.this, R.string.toast_empty_question, Toast.LENGTH_SHORT).show();
        }
    }
    public void isCorrectAnswer(boolean answer){
        if(mQuestionBank[mStackIndex.peek()].isAnswerTrue() == answer){
            Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "On Start");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "on Pause");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "on Resume");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "on Stop");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "on Destroy");
    }
    public void setTextViewQuestionOnSave(){
        mQuestionTextView.setText(mQuestionBank[mStackIndex.peek()].getmRestId());
    }
}