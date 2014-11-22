/*
 * Copyright 2014 Denison.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servidor de bate-papo com socket
 * @author Denison
 */
public class ChatServer implements Runnable {

    private static final String[] COLOR = {
        Print.ANSI_BLUE, Print.ANSI_BACK_RED, Print.ANSI_GREEN, Print.ANSI_PURPLE,
        Print.ANSI_BACK_BLUE, Print.ANSI_BACK_RED, Print.ANSI_BACK_GREEN, Print.ANSI_BACK_PURPLE,
        Print.ANSI_BACK_BLUE + Print.ANSI_YELLOW, Print.ANSI_BACK_BLUE + Print.ANSI_WHITE,
        Print.ANSI_BACK_BLACK + Print.ANSI_YELLOW, Print.ANSI_BACK_BLACK + Print.ANSI_WHITE,
        Print.ANSI_BACK_RED + Print.ANSI_YELLOW, Print.ANSI_BACK_RED + Print.ANSI_WHITE,};

    private static final HashSet<String> usuarios = new HashSet<>();
    private static final ArrayList<String> enviou = new ArrayList<>();
    private static final LinkedList<String> lista = new LinkedList<>();
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private final String user;
    private String cor;

    public ChatServer(DataInputStream in, DataOutputStream out, String user, String cor) {
        this.in = in;
        this.out = out;
        this.user = user;
        this.cor = cor;
    }

    public ChatServer(DataInputStream in, String user, String cor) {
        this.in = in;
        this.user = user;
        this.cor = cor;
    }

    @Override
    public void run() {
        if (out != null && in != null) {
            enviar();
        } else if (in != null) {
            receber();
        }
    }

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(2000);
            Print.ln("Esperando por cliente na porta " + serverSocket.getLocalPort() + "...");
            int indexCor = 0;
            while (true) {
                Socket server = serverSocket.accept();
                Print.ln("Conectado com " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                String user = in.readUTF();
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                if (usuarios.contains(user)) {
                    out.writeUTF(Print.ANSI_BACK_YELLOW + "|                                |" + Print.ANSI_RESET);
                    out.writeUTF(Print.ANSI_BACK_YELLOW + "|       " + Print.ANSI_BACK_RED + "Usuário já existe!" + Print.ANSI_BACK_YELLOW + "       |" + Print.ANSI_RESET);
                    out.writeUTF("[exit]");
                } else {
                    Thread enviarMsg = new Thread(new ChatServer(in, out, user, COLOR[indexCor]));
                    enviarMsg.start();
                    Thread receberMsg = new Thread(new ChatServer(in, user, COLOR[indexCor]));
                    receberMsg.start();
                    indexCor++;
                    if (indexCor == COLOR.length) {
                        indexCor = 0;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void enviar() {
        Print.ln("Iniciando serviço de envio: " + user, cor);
        try {
            String texto;
            do {
                do {
                    Thread.sleep(1000);
                    texto = enviar(user);
                    if (texto == null && !usuarios.contains(user)) {
                        texto = "[exit]";
                    }
                } while (texto == null);
                if ("[exit]".equals(texto)) {
                    out.writeUTF("[exit]");
                } else {
                    out.writeUTF(texto);
                }
            } while (!"[exit]".equals(texto));
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, user + " : " + ex);
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException ex) {
            }
        }
        Print.ln("Finalizando serviço de envio: " + user, cor);
    }

    private void receber() {
        Print.ln("Iniciando serviço de recebimento: " + user, cor);
        addUser(user);
        try {
            String texto;
            do {
                texto = in.readUTF();
                if ("[exit]".equals(texto)) {
                    receber(cor + user + " > saindo" + Print.ANSI_RESET);
                } else {
                    receber(cor + user + " > " + texto + Print.ANSI_RESET);
                }
            } while (!"[exit]".equals(texto));
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, user + " : " + ex);
        }
        removeUser(user);
        Print.ln("Finalizando serviço de recebimento: " + user, cor);
    }

    private synchronized void receber(String string) {
        if (lista.isEmpty()) {
            lista.addLast(string);
        } else {
            lista.addLast(string);
        }
    }

    private synchronized String enviar(String user) throws InterruptedException {
        while (lista.isEmpty()) {
            return null;
        }
        if (!usuarios.contains(user)) {
            return "[exit]";
        }
        if (enviou.contains(user)) {
            return null;
        }
        String r = lista.getFirst();
        enviou.add(user);
        if (enviou.size() >= usuarios.size()) {
            enviou.clear();
            lista.removeFirst();
        }
        return r;
    }

    private synchronized void addUser(String user) {
        usuarios.add(user);
    }

    private synchronized void removeUser(String user) {
        usuarios.remove(user);
    }
}
