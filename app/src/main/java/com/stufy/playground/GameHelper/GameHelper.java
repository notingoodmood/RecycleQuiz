package com.stufy.playground.GameHelper;

import android.util.Log;

import com.stufy.playground.Entity.Question;

import java.util.ArrayList;
import java.util.HashSet;

abstract class GameHelper {
    //游戏的主要支持者模型

    ArrayList<Question> Questions;
    HashSet<Integer> Answered,RightAnswered,WrongAnswered;
    protected int Number_RightAnswered,Number_WrongAnswered;
    public int pointer=-1;

    public abstract void Init();

    public abstract Question getRandomQuestion();

    public abstract Boolean isRight(Question question,String answered);

    protected abstract void AfterAnswerQuestion(int QID,Boolean isOk);

    public abstract Boolean isAnswered(int QID);

    public abstract Question getNextQuestion();

    @Override
    protected void finalize() throws Throwable {
        if(Questions==null || Answered==null || RightAnswered==null || WrongAnswered==null){
            Log.e("fetal","没有初始化!!!");
            return;
        }
        Answered.clear();
        RightAnswered.clear();
        WrongAnswered.clear();
        Questions.clear();
        super.finalize();
    }

}
