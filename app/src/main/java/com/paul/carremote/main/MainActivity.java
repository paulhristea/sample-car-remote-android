package com.paul.carremote.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.paul.carremote.utils.ActivityUtils;
import com.paul.carremote.utils.Constants;

import carremote.paul.com.samplecarremote.R;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private View mButtonLeft;
    private View mButtonRight;
    private View mButtonUp;
    private View mButtonDown;
    private TextView mStatusText;

    private MessageTransmitter mMessageTransmitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.enterFullScreenMode(this);
        setContentView(R.layout.activity_main);

        mMessageTransmitter = MessageTransmitter.getInstance();

        mButtonLeft = findViewById(R.id.button_left);
        mButtonRight = findViewById(R.id.button_right);
        mButtonUp = findViewById(R.id.button_up);
        mButtonDown = findViewById(R.id.button_down);
        mStatusText = (TextView) findViewById(R.id.status_text);

        mButtonLeft.setOnTouchListener(this);
        mButtonRight.setOnTouchListener(this);
        mButtonUp.setOnTouchListener(this);
        mButtonDown.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            mStatusText.setText("");
            return true;
        }

        boolean touchEventHandled = false;

        switch (v.getId()) {
            case R.id.button_left:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mStatusText.setText(R.string.left);
                    mMessageTransmitter.sendMessage(Constants.CODE_LEFT_START);
                    touchEventHandled = true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mMessageTransmitter.sendMessage(Constants.CODE_LEFT_STOP);
                }
                break;
            case R.id.button_right:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mStatusText.setText(R.string.right);
                    mMessageTransmitter.sendMessage(Constants.CODE_RIGHT_START);
                    touchEventHandled = true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mMessageTransmitter.sendMessage(Constants.CODE_RIGHT_STOP);
                }
                break;
            case R.id.button_up:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mStatusText.setText(R.string.up);
                    mMessageTransmitter.sendMessage(Constants.CODE_ACCELERATE_START);
                    touchEventHandled = true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mMessageTransmitter.sendMessage(Constants.CODE_ACCELERATE_STOP);
                }
                break;
            case R.id.button_down:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mStatusText.setText(R.string.down);
                    mMessageTransmitter.sendMessage(Constants.CODE_REVERSE_START);
                    touchEventHandled = true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mMessageTransmitter.sendMessage(Constants.CODE_REVERSE_STOP);
                }
                break;
        }
        return touchEventHandled;
    }

    private void sendMessage(int code) {

    }
}
