package sample.connection;

import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class TCPClient {
    private static Socket socket;
    private static DataOutputStream outputStream;
    private static BufferedReader inputStream;
    private static boolean connectionEstablished = false;

    public static void establishConnection() throws SocketException {
        connectionEstablished = true;
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
            if (!connectionEstablished) TCPClient.establishConnection();
            outputStream.write(message.getBytes());
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

    public static String getServerResponse() {
        String response = null;
        try {
            response = inputStream.readLine();

            if (response.equals("404")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка на сервере");
                alert.setHeaderText("Ошибка!");
                alert.setContentText("Ошибка на сервере");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Запрос обработан успешно");
                alert.setHeaderText("Данные обработаны");
                alert.setContentText("Запрос обработан успешно");
                alert.showAndWait();
            }

            System.out.println("RESPONSE: " + response);
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
