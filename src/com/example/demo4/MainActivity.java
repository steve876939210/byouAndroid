package com.example.demo4;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends Activity {

	public void showPopupWindow(){
    	Button btn = (Button) findViewById(R.id.button1);
    	View layout = getLayoutInflater().inflate(R.layout.pw, null);
    	
    	PopupWindow popupWindow = new PopupWindow(layout, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    	popupWindow.showAsDropDown(btn, 0, -100);
    }
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {//主线程
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {//子线程
			
			@Override
			public void run() {
				runOnUiThread(new Runnable() {//将参数代码放在主线程中执行
					
					@Override
					public void run() {
						showPopupWindow();
					}
				});
			}
		};
		timer.schedule(task, 1000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
