/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Server;

import Global.Action;

/**
 *
 * @author 98920
 */
public class QueueElemet {

    private final int action;
    private final byte[] args;
    private final byte[] data;

    public QueueElemet(Action action, byte[] args, byte[] data) {
        this.action = action.ordinal();
        this.args = args;
        this.data = data;
    }

    public QueueElemet(int action, byte[] args, byte[] data) {
        this.action = action;
        this.args = args;
        this.data = data;
    }

    public int getAction() {
        return action;
    }

    public byte[] getArgs() {
        return args;
    }

    public byte[] getData() {
        return data;
    }

}
