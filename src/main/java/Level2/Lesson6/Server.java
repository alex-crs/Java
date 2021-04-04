package Level2.Lesson6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


public class Server {

    public static final int PORT = 6001;
    public static DataInputStream in = null;
    public static DataOutputStream out = null;
    private static Socket socket;
    private static ServerSocket server;

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            server = new ServerSocket(PORT);
            System.out.printf("Сервер запущен на [%s], порт %s.\n", server.getInetAddress(), PORT);
            socket = server.accept();
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            System.out.printf("Клиент [%s] подключен к серверу.\n", socket.getInetAddress());
            out.writeUTF("Добро пожаловать на сервер!");

            new Thread(() -> {
                try {
                    while (true) {
                        String inText = in.readUTF();
                        if (!"/exit".equals(inText) && (!inText.isEmpty())) {
                            System.out.printf("Клиент [%s]: %s\n", socket.getInetAddress(), inText);
                        }
                    }
                } catch (SocketException|EOFException e) {
                    System.out.println("Клиент отключен.\nСервер остановлен.");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                        out.close();
                        socket.close();
                        server.close();
                        System.exit(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            while (true) {
                String outText = reader.readLine();
                if (!outText.equals("/exit") && (!outText.isEmpty())) {
                    out.writeUTF(outText);
                } else {
                    out.writeUTF("/exit");
                    break;
                }
            }
        } catch (IOException e) {
            System.exit(0);
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
