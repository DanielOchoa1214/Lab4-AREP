package org.arep.taller1.webclient.filehandlers;

import org.arep.taller1.webclient.filehandlers.impl.ErrorResponse;
import org.arep.taller1.webclient.filehandlers.impl.ImageResponse;
import org.arep.taller1.webclient.filehandlers.impl.TextResponse;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class FileHandler {
    private static ResponseInterface responseInterface;

    private static final List<String> supportedImgFormats = Arrays.asList("jpg", "png", "jpeg");

    private static final List<String> supportedTextFormats = Arrays.asList("html", "css", "js");

    /*
    Method in charge of sending the apropiate response based on what the resource request is
     */
    public static void sendResponse(URI resourcePath, Socket clientSocket) throws IOException, URISyntaxException {
        String path = resourcePath.getPath();
        String fileType = getFileType(resourcePath);
        if (path.equals("/target/classes/public/")) {
            responseInterface = new TextResponse(clientSocket, "html", new URI(resourcePath.getPath() + "/index.html"));
        } else if (!fileExists(resourcePath)) {
            responseInterface = new ErrorResponse(clientSocket);
        } else if (isImage(resourcePath)) {
            responseInterface = new ImageResponse(clientSocket, fileType, resourcePath);
        } else if (isText(resourcePath)) {
            responseInterface = new TextResponse(clientSocket, fileType, resourcePath);
        } else {
            responseInterface = new ErrorResponse(clientSocket);
        }
        responseInterface.sendResponse();
    }

    /*
    Method in charge of detecting what is the type of file the client is requesting
     */
    private static String getFileType(URI path) {
        String fileFormat = "";
        try {
            fileFormat = path.getPath().split("\\.")[1];
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return fileFormat;
    }

    /*
    Method that checks if the file requested can be sent in a plain text stream
     */
    private static boolean isText(URI path) {
        String fileFormat = path.getPath().split("\\.")[1];
        return supportedTextFormats.contains(fileFormat);
    }

    /*
    Method that checks if the file requested is an image and should de send using a byte stream
     */
    private static boolean isImage(URI path) {
        String fileFormat = path.getPath().split("\\.")[1];
        return supportedImgFormats.contains(fileFormat);
    }

    /*
    Method that checks if the file requested exists
     */
    public static boolean fileExists(URI path) {
        File file = new File(System.getProperty("user.dir") + path);
        return file.exists();
    }


    private static void sendError(URI resourcePath, Socket clientSocket) throws IOException {
        responseInterface = new ErrorResponse(clientSocket);
        responseInterface.sendResponse();
    }
}
