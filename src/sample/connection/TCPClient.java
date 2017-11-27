package sample.connection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {
    private static Socket socket;
    private static DataOutputStream outputStream;
    private static BufferedReader inputStream;

    public static void establishConnection() {
        try {
            socket = new Socket("localhost", 9876);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

    public static void sendMessage(String message) {
        try {
            outputStream.writeBytes(message);
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

    public static String getServerResponse() {
        String response = null;
        try {
            response = inputStream.readLine();
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
        return response;
    }

    public static void closeSession() {
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
    }
}
