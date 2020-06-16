package com.stufy.playground;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.stufy.playground.GameHelper.RecycleGameHelper;

public class MainActivity extends AppCompatActivity {


    Button button_Press_Me;

    RecycleGameHelper recycleGameHelper;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_Press_Me=findViewById(R.id.button);
        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        button_Press_Me.setOnClickListener((v)-> {
            Log.e("Normal","You have pushed the button!!!");
            Toast toast=Toast.makeText(this,"You have pushed the button!!!",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        });
        new Thread(this::GameInit).start();
    }

    //任何时候将其调用，则重置
    public void GameInit(){
        recycleGameHelper=new RecycleGameHelper();
        recycleGameHelper.Init();
        System.out.println(recycleGameHelper.getNextQuestion().toString());
        Log.i("test",recycleGameHelper.getNextQuestion().toString());
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
