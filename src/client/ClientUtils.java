package client;

import java.io.*;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;

/**
 * Created by Patryk Cholewa on 11.04.2017.
 */

public class ClientUtils {

    private static File checkFilePath( String dirPathString , String fileNameString ) throws IOException{

        File file = new File( dirPathString + "/" + fileNameString );

        if (file.exists() == true) {
            throw new FileAlreadyExistsException( "That file exists!");
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new IOException( e.getMessage() );
        }

        if( file.exists() == false ){
            throw new FileNotFoundException( "Cannot create a file!");
        }

        return file;
    }

    public static void downloadFileFromUrl( String filePathString, String urlString) throws IOException{

        downloadFileFromUrl( "." , filePathString , urlString );

    }

    public static void downloadFileFromUrl(String dirPathString, String fileNameString, String urlString) throws IOException {

        File file = checkFilePath( dirPathString , fileNameString );

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

    public static Process runJarFile( String filePathString ) throws IOException {

        Process process = runJarFile( filePathString  , new String[0] );
        return process;

    }

    public static Process runJarFile( String filePathString , String []args ) throws IOException {

        String []command;

        command = new String[ args.length + 3 ];
        command[0] = "java";
        command[1] = "-jar";
        command[2] = filePathString;

        for( int i = 0 ; i < args.length ; i++ ){
            command[i + 3] = args[ i ];
        }

        return runFile( command );

    }

    private static Process runFile( String []command ) throws IOException{

        ProcessBuilder processBuilder = new ProcessBuilder( command );
        Process process;

        try{
            process =  processBuilder.start();
        } catch ( IOException e ) {
            throw new IOException( e.getMessage() );
        }

        return process;

    }

}

