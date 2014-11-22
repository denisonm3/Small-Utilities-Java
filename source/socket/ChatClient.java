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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cliente de bate-papo com socket
 * @author Denison
 */
public class ChatClient {
    
    public static void main(String[] args) {
        String server = "localhost";
        Integer port = 2000;
        final Scanner scnr = new Scanner(System.in);
        Print.setForeground(Print.ANSI_BACK_YELLOW);
        Print.setBackground(Print.ANSI_BLACK);
        Print.textln("+--------------------------------+");
        Print.textln("|                                |");
        Print.ln("|    Bem vindo ao SocketChat!    |", Print.ANSI_BACK_YELLOW, Print.ANSI_BLUE);
        Print.textln("|                                |");
        Print.textln("+--------------------------------+");
        Print.textln("|                                |");
        Print.textln("|    Informe seu nome:           |");
        Print.str("->   ",Print.ANSI_BACK_RED);
        String nome = scnr.next();
        Print.textln("|    Digite [exit] para sair!    |");
        Print.textln("|                                |");
        Print.textln("+--------------------------------+");
        Print.textln("|                                |");
        Print.textln("|    Conectando ao servidor...   |");
        try {
            Socket client = new Socket(server, port);
            OutputStream outToServer = client.getOutputStream();
            final DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF(nome);
            Thread escrever = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String texto;
                        do {
                            texto = scnr.nextLine();
                            out.writeUTF(texto);
                        } while (!texto.equals("[exit]"));
                    } catch (IOException ex) {
                        Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            escrever.start();
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            String texto;
            do {
                texto = in.readUTF();
                if (texto.equals("[exit]")) {
                    Print.textln("|                                |");
                    Print.textln("|      Finalizando execução      |");
                    Print.textln("|                                |");
                    Print.textln("+--------------------------------+");
                } else {
                    System.out.println(texto);
                }
            } while (!texto.equals("[exit]"));
        } catch (IOException ex) {
            Print.textln("|                                |");
            Print.text("|  ");
            Print.str("  Não foi possível conectar!", Print.ANSI_BACK_RED);
            Print.textln("  |");
            Print.textln("|                                |");
            Print.textln("+--------------------------------+");
        }
        System.exit(0);
    }
}
