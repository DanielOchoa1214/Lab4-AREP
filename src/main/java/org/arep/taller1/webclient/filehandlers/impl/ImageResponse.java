package org.arep.taller1.webclient.filehandlers.impl;

import org.arep.taller1.webclient.filehandlers.ResponseInterface;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;

/**
 * Class in charge of responding with an image through a byte stream
 * @author Daniel Ochoa
 */
public class ImageResponse implements ResponseInterface {
    private Socket clientSocket;
    private String fileType;
    private URI filePath;

    /**
     * Constructor of the ImageResponse Class
     * @param clientSocket Socket where the server established communication with the client
     * @param fileType Type of file the client solicited
     * @param filePath Path to the file solicited
     */
    public ImageResponse(Socket clientSocket, String fileType, URI filePath){
        this.clientSocket = clientSocket;
        this.fileType = fileType;
        this.filePath = filePath;
    }

    /**
     * Method that responds to an image file request
     * @throws IOException In case something goes wrong during the streaming of the response
     */
    @Override
    public void sendResponse() throws IOException {
        OutputStream out = clientSocket.getOutputStream();
        System.out.println(filePath);
        BufferedImage img = ImageIO.read(new File(System.getProperty("user.dir") + filePath));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(img, fileType, byteArrayOutputStream);

        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        out.write(header().getBytes());
        out.write(byteArrayOutputStream.toByteArray());

        out.close();
        clientSocket.close();
    }

    /*
    Method the returns the header, made to simplify the creation of the response
     */
    private String header(){
        return "HTTP/1.1 200 OK \r\n" +
                "Content-Type: image/" + fileType + " \r\n" +
                "\r\n";
    }
}
