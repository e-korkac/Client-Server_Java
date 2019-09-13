package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        while (true) {
            try (ServerSocket server = new ServerSocket(1500);
                    Socket socket = server.accept();
                    InputStream input = socket.getInputStream();
                    OutputStream output = socket.getOutputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(input));) {

                String operands = br.readLine();

                int operand1 = Integer.parseInt(operands.split(",")[0]);
                int operand2 = Integer.parseInt(operands.split(",")[1]);

                String result = String.valueOf(operand1 + operand2);

                output.write((result + "\r\n").getBytes());

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
