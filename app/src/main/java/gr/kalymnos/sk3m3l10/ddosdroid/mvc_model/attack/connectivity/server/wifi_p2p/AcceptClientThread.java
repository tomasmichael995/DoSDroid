package gr.kalymnos.sk3m3l10.ddosdroid.mvc_model.attack.connectivity.server.wifi_p2p;

import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;

import gr.kalymnos.sk3m3l10.ddosdroid.mvc_model.attack.repository.AttackRepository;
import gr.kalymnos.sk3m3l10.ddosdroid.pojos.attack.Attack;
import gr.kalymnos.sk3m3l10.ddosdroid.pojos.attack.Constants;

class AcceptClientThread extends Thread {
    private static final String TAG = "AcceptClientThread";

    private final Attack attack;
    private final AttackRepository attackRepo;
    private final ExecutorService executor;

    private ServerSocket serverSocket;
    private int localPort;

    AcceptClientThread(Attack attack, ExecutorService executor, AttackRepository attackRepo) {
        this.attack = attack;
        this.executor = executor;
        this.attackRepo = attackRepo;
        initializeServerSocket();
        updateAttackWithLocalPort(attack, attackRepo);
    }

    private void initializeServerSocket() {
        try {
            serverSocket = new ServerSocket(0);
            localPort = serverSocket.getLocalPort();
        } catch (IOException e) {
            Log.e(TAG, "Error creating server socket.");
        }
    }

    private void updateAttackWithLocalPort(Attack attack, AttackRepository attackRepo) {
        attack.addSingleHostInfo(Constants.Extra.EXTRA_LOCAL_PORT, String.valueOf(localPort));
        attackRepo.updateAttack(attack);
    }

    public void close(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            Log.e(TAG,"Error when closing server socket");
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                executor.execute(new WifiP2pServerThread(socket));
            } catch (SocketException e) {
                Log.d(TAG, "Error on serverSocket.accept(). Maybe the serverSocket closed.", e);
                break;
            } catch (IOException e) {
                Log.d(TAG, "Error on serverSocket.accept(). Maybe the serverSocket closed", e);
                break;
            }
        }
    }
}