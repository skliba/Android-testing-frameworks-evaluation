package com.example.noxqs.helloandroid;


import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


public class MainActivity extends ActionBarActivity {

    private Button helloWorldButton;
    private int i = 1;
    private RelativeLayout rLayout;
    private int heightButton;
    private int widthButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloWorldButton = (Button) findViewById(R.id.greeting_btn);
        rLayout = (RelativeLayout) findViewById(R.id.rLayout);

        helloWorldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                heightButton = helloWorldButton.getHeight();
                widthButton = helloWorldButton.getWidth();

                helloWorldButton.setText(String.valueOf(i));

                if (i % 2 == 0) helloWorldButton.setTextColor(Color.GREEN);
                else helloWorldButton.setTextColor(Color.BLUE);
                i++;
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        heightButton = helloWorldButton.getHeight();
        widthButton = helloWorldButton.getWidth();
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                return true;

            case R.id.size_btn:
                resizeBtn(item);
                return true;

            case R.id.resizeAnimation_btn:
                resizeAnimation(item);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void resizeAnimation(MenuItem item) {
        int widthRelativeLayout = rLayout.getWidth();
        int heightRelativeLayout = rLayout.getHeight();

        if (item.getTitle().equals("GrowAnimation")) {
            item.setTitle("ShrinkAnimation");

            ResizeAnimation resizeAnimation = new ResizeAnimation(helloWorldButton, widthRelativeLayout, heightRelativeLayout);
            resizeAnimation.setDuration(2000);
            helloWorldButton.startAnimation(resizeAnimation);
        } else {
            item.setTitle("GrowAnimation");

            ResizeAnimation resizeAnimation = new ResizeAnimation(helloWorldButton, widthButton, heightButton);
            resizeAnimation.setDuration(2000);
            helloWorldButton.startAnimation(resizeAnimation);
        }
    }

    private void resizeBtn(MenuItem item) {

        if (item.getTitle().equals("Grow")) {
            item.setTitle("Shrink");

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            helloWorldButton.setLayoutParams(lp);
        } else {
            item.setTitle("Grow");

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            helloWorldButton.setLayoutParams(lp);
        }
    }
}
