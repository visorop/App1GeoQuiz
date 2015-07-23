package com.visorop.app1geoquiz.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    private static final String TAG = QuizActivity.class.getSimpleName();

    private Button mButtonTrue;
    private Button mButtonFalse;
    private Button mButtonCheat;
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
    private static Logger<Integer> logger = new Logger<Integer>();

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
                case R.id.cheat_button : {
                    Intent intent = new Intent(QuizActivity.this,CheatActivity.class);
                    boolean answerIsTrue = mQuestionBank[logger.getCurrent()].isTrueQuestion();
                    intent.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);

                    QuizActivity.this.startActivity(intent);
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

        if(savedInstanceState == null){
            Log.d(TAG,"savedInstanceState is null!!!");
        }else{
            Log.d(TAG,"savedInstanceState is NOT null");
        }

        Log.d(TAG, "onCreate() entered.");

        setContentView(R.layout.activity_quiz);

        mButtonTrue = (Button) this.findViewById(R.id.button_true);
        mButtonTrue.setOnClickListener(onClickListenerQuizActivity);

        mButtonFalse = (Button) this.findViewById(R.id.button_false);
        mButtonFalse.setOnClickListener(onClickListenerQuizActivity);

        mButtonCheat = (Button) this.findViewById(R.id.cheat_button);
        mButtonCheat.setOnClickListener(onClickListenerQuizActivity);

        mButtonNext = (ImageButton) this.findViewById(R.id.button_next);
        mButtonNext.setOnClickListener(onClickListenerQuizActivity);

        mButtonPrev = (ImageButton) this.findViewById(R.id.button_prev);
        mButtonPrev.setOnClickListener(onClickListenerQuizActivity);

        mQuizText = (TextView) this.findViewById(R.id.text_quiz);
        mQuizText.setOnClickListener(onClickListenerQuizActivity);

        if(savedInstanceState == null){
            logger.add(randomIndexGenerator.getNextValue());
        }
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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart() entered");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() entered");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() entered");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() entered");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() entered");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState() entered");

    }
}
