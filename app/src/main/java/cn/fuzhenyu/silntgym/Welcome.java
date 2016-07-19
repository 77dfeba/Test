package cn.fuzhenyu.silntgym;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by 77dfeba on 2016/6/7.
 */

public class Welcome extends Activity {
    private SharedPreferences preferences;
    public void onCreate(Bundle savedInstantState){
        super.onCreate(savedInstantState);
        setContentView(R.layout.welcome);
        preferences = getSharedPreferences("scount", MODE_WORLD_READABLE);//判断是否为首次登陆
        final int scount = preferences.getInt("scount",0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(scount == 0){
                    SharedPreferences.Editor editor = preferences.edit();
                    //存入数据
                    editor.putInt("scount",1);
                    //提交修改
                    editor.apply();
                    Intent intentToWelcome = new Intent(Welcome.this,WhatsNew.class);
                    startActivity(intentToWelcome);
                }
                else {
                    Intent intentToMain = new Intent(Welcome.this,MainActivity.class);
                    startActivity(intentToMain);
                }
                Welcome.this.finish();
            }
        },2000);
    }
}
