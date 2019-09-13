package klient;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Klient {

    public static void main(String[] args) {

        Frame frame = new Frame("Amount");
        frame.setLayout(new FlowLayout());
        frame.setSize(600, 100);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        Label firstLabel = new Label("Enter first operand here");
        Label secondLabel = new Label("Enter second operand here");
        Label resultLabel = new Label("Result");
        TextField firstField = new TextField(10);
        TextField secondField = new TextField(10);
        TextField resultField = new TextField(10);
        Button resButton = new Button("Do it");

        resButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try (Socket socket = new Socket("localhost", 1500);
                        InputStream input = socket.getInputStream();
                        OutputStream output = socket.getOutputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(input));) {

                    String operands = (firstField.getText().trim() + "," + secondField.getText().trim()) + "\r\n";
                    output.write(operands.getBytes());

                    String result = br.readLine();

                    resultField.setText(result);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });

        frame.add(firstLabel);
        frame.add(firstField);
        frame.add(secondLabel);
        frame.add(secondField);
        frame.add(resButton);
        frame.add(resultLabel);
        frame.add(resultField);
        frame.setVisible(true);

    }

}
