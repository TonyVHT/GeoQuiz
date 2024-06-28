package com.example.geoquiz.controller;

import static com.example.geoquiz.Utils.Utilidades.KEY_ANSWER;
import static com.example.geoquiz.Utils.Utilidades.KEY_IS_CHEATING;
import static com.example.geoquiz.Utils.Utilidades.KEY_QUESTION;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import com.example.geoquiz.R;

import org.w3c.dom.Text;

public class CheatActivity extends AppCompatActivity {

    private Button mBtnCheat;
    private TextView mTvAnswer;
    private TextView mTvQuestion;
    private Button mBtnBack;
    private boolean mIsCheater;

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
        setIdWidGets();
        setListenerBtnCheat();
        setListenerBtnBack();
        mIsCheater = false;
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
            }
        });
    }
    public void setListenerBtnBack(){
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                Bundle resultBundle = new Bundle();
                resultBundle.putBoolean(KEY_IS_CHEATING, mIsCheater);
                resultIntent.putExtras(resultBundle);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}