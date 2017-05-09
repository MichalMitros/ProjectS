package client;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;

/**
 * Created by Patryk Cholewa on 05.05.2017.
 */
public class DownloadFileFromUrlAction implements Action {

    private File file;
    private URL url;
    private String info;

    public DownloadFileFromUrlAction( String filePathString , String urlString ){

        try {
            this.file = checkFilePath( filePathString );
            this.url = new URL(urlString);
            info = "DownloadFileFromUrlAction constructed correctly.";
        } catch (IOException e) {
            info = e.getMessage();
        }
    }

    @Override
    public void executeAction() {
        try
        {
            downloadFileFromUrl();
            info = "DownloadFileFromUrl executed correctly.";
        }
        catch (IOException e)
        {
           info = e.getMessage();
        }

    }

    @Override
    public String getInfo() {
        return info;
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

    private static File checkFilePath( String filePathString )
            throws IOException {

        File file = new File( filePathString );

        try {
            file.createNewFile();
        } catch ( IOException e ) {
            throw new IOException( e.getMessage() );
        }

        if( file.exists() == false ){
            throw new FileNotFoundException( "File not created with unknown reason!");
        }

        return file;
    }

}
