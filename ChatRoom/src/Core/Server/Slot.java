/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Server;

import Global.Action;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 98920
 */
public class Slot {

    private final Socket socket;
    private final DataInputStream dis;
    private final ObjectInputStream ois;
    private final DataOutputStream dos;
    private final ObjectOutputStream oos;

    private final Thread readDataThread;
    private final Queue<QueueElemet> slotQueue;

    public Slot(Socket socket) throws IOException {
        this.socket = socket;

        InputStream is = this.socket.getInputStream();
        OutputStream os = this.socket.getOutputStream();

        dis = new DataInputStream(is);
        ois = new ObjectInputStream(is);
        dos = new DataOutputStream(os);
        oos = new ObjectOutputStream(os);

        this.readDataThread = new Thread(new SlotThread());
        slotQueue = new LinkedList<>();

    }

    public void start() {
        readDataThread.start();
    }

    public void Send(QueueElemet element) throws IOException {
        dos.writeInt(element.getAction());

        byte[] args = element.getArgs();
        dos.writeInt(args.length);
        oos.write(args);

        byte[] data = element.getData();
        dos.writeInt(data.length);
        oos.write(data);
    }

    private class SlotThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    int action = dis.readInt();
                    int length;

                    length = dis.readInt();
                    byte[] args = new byte[length];
                    ois.read(args);

                    length = dis.readInt();
                    byte data[] = new byte[length];
                    ois.read(data);

                    QueueElemet queueElemet = new QueueElemet(action, args, data);
                    slotQueue.add(queueElemet);

                } catch (IOException ex) {
                    Logger.getLogger(Slot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
