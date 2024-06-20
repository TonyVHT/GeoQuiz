package com.example.geoquiz.controller;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.geoquiz.R;

import org.w3c.dom.Text;

public class CheatActivity extends AppCompatActivity {

    private static final String KEY_ANSWER = "com.example.geoquiz.answer";
    private static final String KEY_QUESTION ="com.example.geoquiz.question";
    private static final String KEY_IS_CHEATER = "com.example.geoquiz.cheater";
    private Button mBtnCheat;
    private TextView mTvAnswer;
    private TextView mTvQuestion;
    private Button mBtnBack;
    private boolean isCheater;

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
        isCheater = false;
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
                boolean answer = getIntent().getBooleanExtra(KEY_ANSWER, false);
                int resIdQuestion = getIntent().getIntExtra(KEY_QUESTION, -1);
                if(answer){
                    mTvAnswer.setText(R.string.text_view_answer_true);
                }else{
                    mTvAnswer.setText(R.string.text_view_answer_false);
                }
                if(resIdQuestion != -1){
                    mTvQuestion.setText(resIdQuestion);
                }else{
                    Toast.makeText(CheatActivity.this, "No question found", Toast.LENGTH_SHORT).show();
                }
                isCheater = true;
            }
        });
    }
    public void setListenerBtnBack(){
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivityMain = new Intent(CheatActivity.this, MainActivity.class);
                newActivityMain.putExtra(KEY_IS_CHEATER, isCheater);
                //TODO establecer contrato
                //ActivityResultContract
            }
        });
    }
}