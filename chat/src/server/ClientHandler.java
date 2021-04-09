package server;

import client.Controller;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nickName;


    public ClientHandler(Server server, Socket socket) {

        try {
            this.server = server;
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            //new Thread(() -> {
                server.getExecutorService().execute(() -> {
                    try {
                        // цикл аутентифиукации
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/auth")) {
                                String[] token = str.split("\\s");
                                String newNick = server.getAuthService()
                                        .getNickByLoginAndPassword(token[1], token[2]);
                                if (newNick != null) {
                                    nickName = newNick;
                                    sendMsg("/authok " + nickName);
                                    server.subscribe(this);
                                    System.out.println("Клиент " + nickName + " подключился");
                                    showHistory();
                                    break;
                                } else {
                                    sendMsg("Неверный логин / пароль");
                                }
                            }
                        }

                        //цикл работы
                        while (true) {
                            String str = in.readUTF();

                            saveMsg(str);
                            if (str.startsWith("/")) {
                                System.out.println(str);
                                if (str.equals("/end")) {
                                    out.writeUTF("/end");
                                    break;
                                }
                                if (str.startsWith("/w")) {
                                    String[] token = str.split("\\s+", 3);
                                    if (token.length < 3) {
                                        continue;
                                    }
                                    server.privateMsg(this, token[1], token[2]);
                                }
                            } else {
                                server.broadcastMsg(this, str);

                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("Клиент отключился");
                        server.unsubscribe(this);
                        try {
                            socket.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    //  }).start();
                });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showHistory() throws IOException {
        FileReader reader = new FileReader("files/historyChat.txt");
        Scanner scanner = new Scanner(reader);

        int counter = 0;
        ArrayList<String> arrayList = new ArrayList<>();
        while (scanner.hasNextLine()) {
                arrayList.add(scanner.nextLine());
            counter++;
        }
        reader.close();

        for (int i = 0; i < arrayList.size(); i++) {
            if (i >= arrayList.size() - 100) {
                sendMsg(arrayList.get(i));
            }

        }
    }

    private void saveMsg(String str) {
        try (FileWriter writer = new FileWriter("files/historyChat.txt", true)) {
            writer.write(str);
            writer.append('\n');

            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNickName(){
        return nickName;
    }
}
