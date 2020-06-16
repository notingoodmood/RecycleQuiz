package com.stufy.playground.GameHelper;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stufy.playground.Entity.Question;
import com.stufy.playground.IOHelper.COSHelper;

import java.util.ArrayList;
import java.util.HashSet;


public class RecycleGameHelper extends GameHelper {
    @Override
    public void Init() {
        JSONObject RawData= COSHelper.ReadJSON("Questions.json");
        if(RawData==null){
            Log.e("Error","看起来问题文件已经木大了...");
            return;
        }
        int QID=100;
        Log.e("l",RawData.toJSONString());
        this.pointer=QID;
        this.Questions=new ArrayList<>();
        JSONArray questionArray=RawData.getJSONArray("Questions");
        for(Object q : questionArray){
            JSONObject j=(JSONObject)q;
            this.Questions.add(new Question(
                    QID++,
                    j.getString("Title"),
                    j.getString("Answer"),
                    j.getJSONArray("Options").toArray(new String[]{})
            ));
        }
        this.Answered=new HashSet<>();
        this.RightAnswered=new HashSet<>();
        this.WrongAnswered=new HashSet<>();
        this.Number_RightAnswered=0;
        this.Number_WrongAnswered=0;
    }

    //先不实现随机获取题目
    @Override
    public Question getRandomQuestion() {
        return null;
    }


    @Override
    public Boolean isRight(Question question,String answered) {
        return question.isRight(answered);
    }

    @Override
    synchronized protected void AfterAnswerQuestion(int QID,Boolean isOk) {
        if(isOk){
            Number_RightAnswered++;
            RightAnswered.add(QID);
        }else{
            Number_WrongAnswered++;
            WrongAnswered.add(QID);
        }
    }

    @Override
    public Boolean isAnswered(int QID) {
        return (RightAnswered.contains(QID) || WrongAnswered.contains(QID));
    }

    @Override
    public Question getNextQuestion() {
        if(!(pointer-100>=Questions.size())){
            return Questions.get((pointer++)-100);
        }else {
            Log.e("Error","问题已经用尽!");
            return null;
        }
    }
}
