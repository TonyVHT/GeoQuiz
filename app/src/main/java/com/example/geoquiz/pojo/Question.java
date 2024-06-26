package com.example.geoquiz.pojo;

import android.widget.Button;
import android.widget.TextView;

public class Question {
    private int mRestId;
    private boolean mAnswerTrue;
    private Button mNextButton;
    private TextView mQuestionTextView;
    public Question(int mRestId, boolean mAnswerTrue){
        this.mRestId = mRestId;
        this.mAnswerTrue = mAnswerTrue;
    }
    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }
    public int getmRestId() {
        return mRestId;
    }
    public void setmRestId(int mRestId) {
        this.mRestId = mRestId;
    }
    public void setmAnswerTrue(boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }
}
