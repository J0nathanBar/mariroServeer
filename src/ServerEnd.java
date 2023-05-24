
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class ServerEnd extends JFrame {
    JLabel label;
    static int port = 8083;
    static final int playerLimit = 2;
    ServerSocket server;
    Socket socket;
    int playerCount = 0;

    public ServerEnd() throws IOException {
        setTitle("Server");
        getContentPane().setBackground(Color.ORANGE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 100);
        setResizable(false);
        setVisible(true);

        serverConnection();
    }

    public void serverConnection() throws IOException {
        server = new ServerSocket(port);
        System.out.println("ServerSocket awaiting connections...");
        int clientsRemaining = playerLimit - playerCount;
        label = new JLabel("Waiting for "+clientsRemaining+" clients ");
        label.setFont(new Font("Verdana", Font.PLAIN, 18));
        add(label);

        while (playerCount < playerLimit) {
            System.out.println("Waiting for accept");
            // Waiting for clients to connect until the number of clients reached player limit
            socket = server.accept();
            clientsRemaining = playerLimit - playerCount;
            label.setText("Waiting for " + clientsRemaining + " Clients");
            //a handler represents a client
            Handler h = new Handler(socket);

            playerCount++;
            System.out.println("Accepted Client:" + playerCount);
        }

        startHandlers();

        sendStart();
    }


    private void startHandlers() {
        for (int i = 1; i < Handler.clients.size(); i++) {
            Handler.clients.get(i).start();
        }
    }

    public static void main(String[] args) {
        try {
            new ServerEnd();
        } catch (IOException e) {
            System.out.println("caught an exception try to disconnect from the server");
        }

    }

    private void sendStart() {
        Handler.sendData(playerCount);
    }

}
