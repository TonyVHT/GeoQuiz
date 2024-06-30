package com.example.geoquiz.controller;

import static com.example.geoquiz.Utils.Utilidades.KEY_ANSWER;
import static com.example.geoquiz.Utils.Utilidades.KEY_ID_ANSWER_SAVED;
import static com.example.geoquiz.Utils.Utilidades.KEY_ID_QUESTION_SAVED;
import static com.example.geoquiz.Utils.Utilidades.KEY_IS_CHEATING;
import static com.example.geoquiz.Utils.Utilidades.KEY_QUESTION;
import static com.example.geoquiz.Utils.Utilidades.KEY_USER_IS_CHEATING;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.geoquiz.R;

import org.w3c.dom.Text;

public class CheatActivity extends AppCompatActivity {
    private Button mBtnCheat;
    private TextView mTvAnswer;
    private TextView mTvQuestion;
    private Button mBtnBack;
    private boolean mIsCheater;
    OnBackPressedDispatcher onBackPressedDispatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cheat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setOnBackPressedDispatcher();
        setIdWidGets();
        setListenerBtnCheat();
        setListenerBtnBack();
        mIsCheater = false;
        if(savedInstanceState != null){
            mIsCheater = savedInstanceState.getBoolean(KEY_USER_IS_CHEATING);
            mTvQuestion.setText(savedInstanceState.getCharSequence(KEY_ID_QUESTION_SAVED));
            mTvAnswer.setText(savedInstanceState.getCharSequence(KEY_ID_ANSWER_SAVED));
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_USER_IS_CHEATING, mIsCheater);
        savedInstanceState.putCharSequence(KEY_ID_QUESTION_SAVED, mTvQuestion.getText());
        savedInstanceState.putCharSequence(KEY_ID_ANSWER_SAVED, mTvAnswer.getText());
    }
    public void setOnBackPressedDispatcher(){
        onBackPressedDispatcher = getOnBackPressedDispatcher();
        onBackPressedDispatcher.addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                backToMainActivity();
            }
        });
    }
    public void setIdWidGets(){
        mBtnCheat = findViewById(R.id.btn_cheat);
        mTvAnswer = findViewById(R.id.tv_answer);
        mTvQuestion = findViewById(R.id.tv_question);
        mBtnBack = findViewById(R.id.btn_back);
    }
    public void setListenerBtnCheat(){
        mBtnCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsCheater = true;
                boolean answerQuestion = getIntent().getBooleanExtra(KEY_ANSWER, false);
                int resIdQuestion = getIntent().getIntExtra(KEY_QUESTION, -1);
                if(resIdQuestion != -1){
                    mTvAnswer.setText(Boolean.toString(answerQuestion));
                    mTvQuestion.setText(getString(resIdQuestion));
                }
                animacionBtnCheat();
            }
        });
    }
    public void setListenerBtnBack(){
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainActivity();
            }
        });
    }
    public void backToMainActivity(){
        Intent resultIntent = new Intent();
        Bundle resultBundle = new Bundle();
        resultBundle.putBoolean(KEY_IS_CHEATING, mIsCheater);
        resultIntent.putExtras(resultBundle);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
    public void animacionBtnCheat(){
        int xPos = mBtnCheat.getWidth() / 2;
        int yPos = mBtnCheat.getHeight() / 2;
        float radious = Math.max(mBtnCheat.getWidth(), mBtnCheat.getHeight()) / 2f;
        Animator animationBtnCheat = ViewAnimationUtils.createCircularReveal(mBtnCheat, xPos, yPos, radious, 0);
        animationBtnCheat.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mBtnCheat.setVisibility(View.INVISIBLE);
            }
        });
        animationBtnCheat.start();
    }

}