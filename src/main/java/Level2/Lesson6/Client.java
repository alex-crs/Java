package Level2.Lesson6;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

public class Client {
    private static final int PORT = 6001;
    private static final String ADDRESS = "127.0.0.1";
    private static DataInputStream in;
    private static DataOutputStream out;
    private static Socket socket;

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            socket = new Socket(ADDRESS, PORT);
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    in = new DataInputStream(socket.getInputStream());
                    while (true) {
                        String inText = in.readUTF();
                        if (!"/exit".equals(inText) && !inText.isEmpty()) {
                            System.out.printf("Сервер [%S]: %s\n", socket.getInetAddress(), inText);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Связь с сервером потеряна.");
                    System.exit(0);
                } finally {
                    try {
                        in.close();
                        out.close();
                        socket.close();
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
        } catch (ConnectException e) {
            System.out.printf("Сервер по адресу [%s]:%s недоступен\n", ADDRESS, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException | RuntimeException e) {
                System.exit(0);
            }
        }
    }
}
