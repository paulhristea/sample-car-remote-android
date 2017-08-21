package com.paul.carremote.main;

import android.util.Log;

import com.paul.carremote.utils.Constants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by CodegileS1 on 20.08.2017.
 */

public class MessageTransmitter {

    private static MessageTransmitter INSTANCE;

    private MessageTransmitter() {
    }

    public static MessageTransmitter getInstance() {
        return INSTANCE != null ? INSTANCE : (INSTANCE = new MessageTransmitter());
    }

    public void sendMessage(int code) {
        try {
            Socket socket = new Socket(Constants.ADDRESS, Constants.PORT);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeInt(code);
        } catch (IOException e) {
            Log.e(MessageTransmitter.class.getName(), "IOException when writing to socket!", e);
        }
    }

}
