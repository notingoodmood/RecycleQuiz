package com.stufy.playground.Entity;

import java.util.ArrayList;
import java.util.Collections;

public class Question {
    //保存一个问题和四个选项，以及它的答案。
    public final int QID;
    private String Question,Answer;
    private ArrayList<String> Options=new ArrayList<>();

    public ArrayList<String> getOptions() {
        return Options;
    }

    public Question(int qid, String question, String answer, String[] options) {
        QID = qid;
        Question=question;
        Answer=answer;
        Collections.addAll(Options, options);
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    //返回答案是否正确
    public Boolean isRight(String testAnswer){
        return testAnswer.equals(Answer);
    }


    @Override
    public String toString() {
        return "Question{" +
                "QID=" + QID +
                ", Question='" + Question + '\'' +
                ", Answer='" + Answer + '\'' +
                ", Options=" + Options +
                '}';
    }
}
