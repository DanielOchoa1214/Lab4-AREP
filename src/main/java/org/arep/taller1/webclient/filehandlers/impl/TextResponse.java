package org.arep.taller1.webclient.filehandlers.impl;

import org.arep.taller1.webclient.filehandlers.ResponseController;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TextResponse implements ResponseController {
    private Socket clientSocket;

    public TextResponse(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void sendResponse() throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String outputLine;

        outputLine = "HTTP/1.1 200 OK \r\n" +
                    "Content-Type: text/html \r\n" +
                    "\r\n";
        outputLine += readFile("./target/classes/public/index.html");

        out.println(outputLine);

        out.close();
        clientSocket.close();
    }

    public static String readFile(String path){
        StringBuilder textFile = new StringBuilder();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                textFile.append(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return textFile.toString();
    }

}
