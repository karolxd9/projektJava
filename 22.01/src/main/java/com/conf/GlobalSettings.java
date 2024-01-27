package com.conf;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GlobalSettings {
    public static SystemInfo info = new SystemInfo();
    public static ExecutorService exec;

    public static Socket socket;
    public static Socket socket2;

    static {
        exec = Executors.newFixedThreadPool(info.getNumberOfCore());
        try {
            socket = new Socket("192.168.0.104", 60000);
        }
        catch(UnknownHostException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
