package com.visorop.app1geoquiz.activities;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.visorop.app1geoquiz.R;

/**
 * Created by veselin.demirev on 23.7.2015.
 */
public class CheatActivity extends android.app.Activity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.visorop.qpp1geoquiz..answer_is_true";

    private boolean answerIsTrue;
    private TextView textAnswerTextView;
    private Button buttonShowAnswer;

    private OnClickListener cheatActivityOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.showAnswerButton : {
                    if(answerIsTrue){
                        textAnswerTextView.setText(R.string.action_true);
                    }else{
                        textAnswerTextView.setText(R.string.action_false);
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_cheat);

        answerIsTrue = this.getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        textAnswerTextView = (TextView) this.findViewById(R.id.answerTextView);

        buttonShowAnswer = (Button) this.findViewById(R.id.showAnswerButton);
        buttonShowAnswer.setOnClickListener(cheatActivityOnClickListener);
    }
}
