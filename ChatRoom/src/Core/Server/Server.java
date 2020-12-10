/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author 98920
 */
public class Server {

    private final ServerSocket serverSocket;
    private final Queue<QueueElemet> serverQueue;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverQueue = new LinkedList<>();
    }

    public void AddActionToQueue(QueueElemet action) {
        this.serverQueue.add(action);
    }

}
