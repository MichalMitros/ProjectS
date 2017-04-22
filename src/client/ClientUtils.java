package client;

import java.io.*;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;

/**
 * Created by Patryk Cholewa on 11.04.2017.
 */

public class ClientUtils {

    public static void downloadFileFromUrl(String dirPathString, String fileNameString, String urlString) throws IOException {

        File dir = new File(dirPathString);
        File file = new File(dirPathString + "/" + fileNameString);

        if (dir.exists() == false) {
            throw new FileNotFoundException("Directory not found!");
        }

        if (file.exists() == true) {
            throw new FileAlreadyExistsException("That file exists!");
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new IOException( e.getMessage() );
        }

        try {
            URL url = new URL(urlString);
            BufferedInputStream inStream = new BufferedInputStream(url.openStream());
            FileOutputStream outStream = new FileOutputStream(file);
            byte buf[] = new byte[2048];
            int i;

            while ((i = inStream.read(buf, 0, 2048)) != -1) {
                outStream.write(buf, 0, i);
            }

            outStream.close();
            inStream.close();
        } catch (IOException e) {
            throw new IOException( e.getMessage() );
        }
    }

    public static Process runJarFile( String PathString ) throws IOException {

        String []command;
        ProcessBuilder processBuilder;
        Process process;

        command = new String[ 3 ];
        command[0] = "java";
        command[1] = "-jar";
        command[2] = PathString;

        processBuilder = new ProcessBuilder( command );

        try{
            process =  processBuilder.start();
        } catch ( IOException e ) {
            throw new IOException( e.getMessage() );
        }

        return process;

    }

    public static Process runJarFile( String PathString , String []args ) throws IOException {

        String []command;
        ProcessBuilder processBuilder;
        Process process;

        command = new String[ args.length + 3 ];
        command[0] = "java";
        command[1] = "-jar";
        command[2] = PathString;

        for( int i = 0 ; i < args.length ; i++ ){
            command[i + 3] = args[ i ];
        }

        processBuilder = new ProcessBuilder( command );

        try{
            process =  processBuilder.start();
        } catch ( IOException e ) {
            throw new IOException( e.getMessage() );
        }

        return process;

    }

}

