package com.visorop.app1geoquiz.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.visorop.app1geoquiz.R;
import com.visorop.app1geoquiz.helper.Logger;
import com.visorop.app1geoquiz.helper.RandomIndexGenerator;
import com.visorop.app1geoquiz.model.TrueFalse;


public class QuizActivity extends Activity {

    private Button mButtonTrue;
    private Button mButtonFalse;
    private ImageButton mButtonNext;
    private ImageButton mButtonPrev;
    private TextView mQuizText;

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true),
    };

    private RandomIndexGenerator randomIndexGenerator = new RandomIndexGenerator(mQuestionBank.length-1);
    private Logger<Integer> logger = new Logger<Integer>();

    private View.OnClickListener onClickListenerQuizActivity = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch(id){
                // add more cases for more UI element`s click events
                case R.id.button_true : {
                    if (mQuestionBank[logger.getCurrent()].isTrueQuestion() == true) {
                        Toast.makeText(QuizActivity.this, R.string.toast_correct, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(QuizActivity.this, R.string.toast_incorrect, Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case R.id.button_false : {
                    if (mQuestionBank[logger.getCurrent()].isTrueQuestion() == false) {
                        Toast.makeText(QuizActivity.this, R.string.toast_correct, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(QuizActivity.this, R.string.toast_incorrect, Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case R.id.text_quiz :
                case R.id.button_next : {
                    logger.goForwardAndAddIfNoMore(randomIndexGenerator.getNextDifferentValue(logger.getCurrent()));
                    mQuizText.setText(mQuestionBank[logger.getCurrent()].getQuestion());
                    break;
                }
                case R.id.button_prev : {
                    if(logger.goBack() < 0) {
                        Toast.makeText(QuizActivity.this, "No more previous questions.", Toast.LENGTH_SHORT).show();
                    }else {
                        mQuizText.setText(mQuestionBank[logger.getCurrent()].getQuestion());
                    }
                    break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mButtonTrue = (Button) this.findViewById(R.id.button_true);
        mButtonTrue.setOnClickListener(onClickListenerQuizActivity);

        mButtonFalse = (Button) this.findViewById(R.id.button_false);
        mButtonFalse.setOnClickListener(onClickListenerQuizActivity);

        mButtonNext = (ImageButton) this.findViewById(R.id.button_next);
        mButtonNext.setOnClickListener(onClickListenerQuizActivity);

        mButtonPrev = (ImageButton) this.findViewById(R.id.button_prev);
        mButtonPrev.setOnClickListener(onClickListenerQuizActivity);

        mQuizText = (TextView) this.findViewById(R.id.text_quiz);
        mQuizText.setOnClickListener(onClickListenerQuizActivity);

        logger.add(randomIndexGenerator.getNextValue());
        mQuizText.setText(mQuestionBank[logger.getCurrent()].getQuestion());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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
