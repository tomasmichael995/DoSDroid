package gr.kalymnos.sk3m3l10.ddosdroid.mvc_model.connectivity.server.nsd;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import gr.kalymnos.sk3m3l10.ddosdroid.mvc_model.connectivity.server.Server;

public class NsdServerThread implements Runnable {
    private static final String TAG = "NsdServerThread";

    private Socket socket;
    private PrintWriter out;

    public NsdServerThread(@NonNull Socket socket) {
        this.socket = socket;
        out = new PrintWriter(getOutputStreamFrom(socket), true);
    }

    private OutputStream getOutputStreamFrom(@NonNull Socket socket) {
        try {
            return socket.getOutputStream();
        } catch (IOException e) {
            Log.e(TAG, "Error obtaining outStream from socket", e);
            return null;
        }
    }

    @Override
    public void run() {
        out.println(Server.RESPONSE);
        closeSocket();
    }

    private void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
