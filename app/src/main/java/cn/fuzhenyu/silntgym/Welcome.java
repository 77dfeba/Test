package cn.fuzhenyu.silntgym;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by 77dfeba on 2016/6/7.
 */

public class Welcome extends Activity {
    public void onCreate(Bundle savedInstantState){
        super.onCreate(savedInstantState);
        setContentView(R.layout.welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcome.this,WhatsNew.class);
                startActivity(intent);
                Welcome.this.finish();
            }
        },2000);
    }
}
