package com.example.ailatrieuphu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.style.QuoteSpan;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvNumberQuestion, tvContentQuestion, tvAnswer1, tvAnswer2, tvAnswer3, tvAnswer4;
    private List<Question> mListQuestion;
    private int currentQusetion = 0;
    private Question mQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        mListQuestion = getListQusetion();
        if (mListQuestion.isEmpty()){
            return;
        }
        setDataQuestion(mListQuestion.get(currentQusetion));
    }

    private void setDataQuestion(Question question) {
        if(question == null){
            return;
        }
        mQuestion = question;

        tvAnswer1.setBackgroundResource(R.drawable.bg_blue_conner_30);
        tvAnswer2.setBackgroundResource(R.drawable.bg_blue_conner_30);
        tvAnswer3.setBackgroundResource(R.drawable.bg_blue_conner_30);
        tvAnswer4.setBackgroundResource(R.drawable.bg_blue_conner_30);


        String titleQuestion = "Question" + question.getNumber();
        tvNumberQuestion.setText(titleQuestion);
        tvContentQuestion.setText(question.getContent());
        tvAnswer1.setText(question.getListAnswer().get(0).getContent());
        tvAnswer2.setText(question.getListAnswer().get(1).getContent());
        tvAnswer3.setText(question.getListAnswer().get(2).getContent());
        tvAnswer4.setText(question.getListAnswer().get(3).getContent());

        tvAnswer1.setOnClickListener(this);
        tvAnswer2.setOnClickListener(this);
        tvAnswer3.setOnClickListener(this);
        tvAnswer4.setOnClickListener(this);

    }

    private void initUi(){
     tvNumberQuestion = findViewById(R.id.tv_number_question);
     tvContentQuestion = findViewById(R.id.tv_content_question);
     tvAnswer1 = findViewById(R.id.tv_answer1);
     tvAnswer2 = findViewById(R.id.tv_answer2);
     tvAnswer3 = findViewById(R.id.tv_answer3);
     tvAnswer4 = findViewById(R.id.tv_answer4);
    }
    private List<Question> getListQusetion() {
       List<Question> list = new ArrayList<>();
        List<Answer> answerlist1 = new ArrayList<>();
         answerlist1.add(new Answer("Gà", true));
         answerlist1.add(new Answer("Cá", false));
         answerlist1.add(new Answer("Bò", false));
         answerlist1.add(new Answer("Dê", false));

        List<Answer> answerlist2 = new ArrayList<>();
         answerlist1.add(new Answer("Tay", false));
         answerlist1.add(new Answer("Trứng", false));
         answerlist1.add(new Answer("Gạch", false));
         answerlist1.add(new Answer("Đá", true));

        List<Answer> answerlist3 = new ArrayList<>();
         answerlist1.add(new Answer("Hà Nội", true));
         answerlist1.add(new Answer("Vinh", false));
         answerlist1.add(new Answer("Hồ Chí Minh", false));
         answerlist1.add(new Answer("Thanh Hóa", false));

        List<Answer> answerlist4 = new ArrayList<>();
         answerlist1.add(new Answer("HUST", true));
         answerlist1.add(new Answer("NEU", false));
         answerlist1.add(new Answer("NUCE", false));
         answerlist1.add(new Answer("HAUI", false));

         list.add(new Question("Con nào là gia cầm", 1, answerlist1));
         list.add(new Question("Chân cứng ... mềm", 2, answerlist2));
         list.add(new Question("Thủ đô của Việt Nam là?", 3, answerlist3));
         list.add(new Question("MHoang học trường nào", 4, answerlist4));

       return list;
    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.tv_answer1:
            tvAnswer1.setBackgroundResource(R.drawable.bg_orange_conner_130);
            checkAnswer(tvAnswer1, mQuestion, mQuestion.getListAnswer().get(0));
            break;
        case R.id.tv_answer2:
            tvAnswer2.setBackgroundResource(R.drawable.bg_orange_conner_130);
            checkAnswer(tvAnswer2, mQuestion, mQuestion.getListAnswer().get(1));
            break;
        case R.id.tv_answer3:
            tvAnswer3.setBackgroundResource(R.drawable.bg_orange_conner_130);
            checkAnswer(tvAnswer3, mQuestion, mQuestion.getListAnswer().get(2));
            break;
        case R.id.tv_answer4:
            tvAnswer4.setBackgroundResource(R.drawable.bg_orange_conner_130);
            checkAnswer(tvAnswer4, mQuestion, mQuestion.getListAnswer().get(3));
            break;
    }
    }
    private void checkAnswer(TextView textView, Question question, Answer answer){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(answer.isCorrect()){
                    textView.setBackgroundResource(R.drawable.bg_blue_conner_30);
                    nextQuestion();
                }else {
                    textView.setBackgroundResource(R.drawable.bg_red_conner_30);
                    showAnswerCorrect(question);
                    gameOver();
                }
            }
        }, 1000);



    }

    private void showAnswerCorrect(Question question) {
        if(question == null || question.getListAnswer() == null || question.getListAnswer().isEmpty()){
            return;
        }
        if (question.getListAnswer().get(0).isCorrect()){
            tvAnswer1.setBackgroundResource(R.drawable.bg_green_conner_30);
        } else if (question.getListAnswer().get(1).isCorrect()){
            tvAnswer2.setBackgroundResource(R.drawable.bg_green_conner_30);
        } else if (question.getListAnswer().get(2).isCorrect()){
            tvAnswer3.setBackgroundResource(R.drawable.bg_green_conner_30);
        } else if (question.getListAnswer().get(3).isCorrect()){
            tvAnswer4.setBackgroundResource(R.drawable.bg_green_conner_30);
        }
    }

    private void nextQuestion() {
        if(currentQusetion == mListQuestion.size() - 1){
            showDialog("You Win");
        } else {
            currentQusetion++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setDataQuestion(mListQuestion.get(currentQusetion));
                }
            },1000);
        }
    }

    private void gameOver() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialog("Game Over");
            }
        },1000);
    }
    private void showDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currentQusetion = 0;
                setDataQuestion(mListQuestion.get(currentQusetion));
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}