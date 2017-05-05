package client;

import botlogic.Bot;

import java.io.*;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;

/**
 * Created by Patryk Cholewa on 05.05.2017.
 */
public class DownloadFileFromUrlAction implements Action {

    File file;
    URL url;

    @Override
    public void constructor(Bot bot)
    {

        this.file = checkFilePath( dirPathString , fileNameString );
        this.url = new URL( urlString );

    }

    @Override
    public void executeAction()
    {
        try
        {
            downloadFileFromUrl();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private void downloadFileFromUrl() throws IOException {

        try {
            BufferedInputStream inStream = new BufferedInputStream( url.openStream() );
            FileOutputStream outStream = new FileOutputStream( file );
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

    private static File checkFilePath( String dirPathString , String fileNameString ) throws IOException{

        File file = new File( dirPathString + "/" + fileNameString );

        try {
            file.createNewFile();
        } catch ( IOException e ) {
            throw new IOException( e.getMessage() );
        }

        if( file.exists() == false ){
            throw new FileNotFoundException( "Cannot create a file!");
        }

        return file;
    }

}
