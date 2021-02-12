package android.example.triviaquiz;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
	
	static TextView counter;
	static int count = 0;
	TextView timer;
	int countTimer = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_layout);
		counter = findViewById(R.id.counter);
		timer = findViewById(R.id.timer);
		timer.setText(String.valueOf(countTimer));
		count = 0;
		counter.setText(count + "");
		
		new CountDownTimer(120000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				timer.setText(getCountTimer(millisUntilFinished / 1000));
				countTimer++;
			}
			
			@Override
			public void onFinish() {
				timer.setText("TIME OVER");
			}
		}.start();
		ArrayList<Question> questions = MainActivity.questions;
		ListView listView = findViewById(R.id.questions);
		
		
		for (Question question : questions) {
			Log.v("question", question.toString());
		}
		
		QuestionAdapter adapter = new QuestionAdapter(this, 0, questions);
		listView.setAdapter(adapter);
	}
	
	public String getCountTimer(long rem) {
		long min = rem / 60;
		long sec = rem - min * 60;
		return String.format("%02d:%02d", min, sec);
	}
	
	static void incrementCounter() {
		count += 1;
		counter.setText("" + count);
	}
	
}