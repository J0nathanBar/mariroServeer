

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Handler extends Thread {

    static ArrayList<Handler> clients = new ArrayList<>();
    static ArrayList<com.company.Data> data = new ArrayList<>();
    private Socket socket;
    InputStream inputStream;
    ObjectInputStream objectinputStream;
    OutputStream outputStream;
    ObjectOutputStream objectOutputStream;

    public Handler(Socket socket) {
        try {
            this.socket = socket;

            this.outputStream = this.socket.getOutputStream();
            this.objectOutputStream = new ObjectOutputStream(this.outputStream);

            this.inputStream = this.socket.getInputStream();
            this.objectinputStream = new ObjectInputStream(inputStream);

            addClientToArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addClientToArray() throws IOException {
        clients.add(this);
        com.company.Data d = new com.company.Data((byte) clients.indexOf(this));
        data.add(d);
        // send player index to client
        clients.get(data.indexOf(d)).objectOutputStream.writeObject(d);

    }

    public void run() {
        while (true) {
            for (byte i = 0; i < clients.size(); i++) {
                Constants.sleep(1);
                com.company.Data d = getData(i);
                Constants.sleep(1);
                sendData(d);
            }
        }
    }

    public com.company.Data getData(byte index) {
        com.company.Data d = new com.company.Data(index);
        Object o = null;
        try {
            synchronized (clients.get(index)) {//read from one client at a time
                o = clients.get(index).objectinputStream.readObject();
            }
            if (o instanceof com.company.Data) {
                d = (com.company.Data) o;

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InternalError e) {
            e.printStackTrace();
        }

        return d;
    }

    static public void sendData(Integer num) {
        for (Handler client : clients) {
            try {
                // send numerical data to each client (playerCounter, usually)
                client.objectOutputStream.writeObject(num);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static public void sendData(com.company.Data d) {
        // sends data to all clients except the sender
        for (int i = 0; i < clients.size(); i++) {
            try {
                if (d.getPlayerIndex() != i) {

                    if (d != null) {
                        clients.get(i).objectOutputStream.writeObject(d);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}