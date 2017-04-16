import java.io.*;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;

/**
 * Created by Patryk Cholewa on 11.04.2017.
 */

public class ClientUtils {

    public static void downloadFileFromUrl( String dirPathString , String fileNameString , String urlString )
            throws IOException {

        File dir = new File( dirPathString );
        File file = new File( dirPathString + "/" + fileNameString );

        if( dir.exists() == false ){
            throw new FileNotFoundException( "Directory not found!" );
        }

        if( file.exists() == true ) {
            throw new FileAlreadyExistsException( "That file exists!" );
        }

        try {
            file.createNewFile();
        } catch ( IOException e ) {
            throw new IOException( "Cannot create that file!" );
        }

        try {
            URL url = new URL( urlString );
            BufferedInputStream inStream = new BufferedInputStream(url.openStream());
            FileOutputStream outStream = new FileOutputStream( file );
            byte buf[] = new byte[2048];
            int i = 0;

            while( ( i = inStream.read( buf , 0 , 2048 ) ) != -1 ){
                outStream.write( buf , 0 , i );
            }

            outStream.close();
            inStream.close();
        } catch ( IOException e ){
            throw new IOException( "Cannot download a file!");
        }
    }

    /*
    public static void main( String []args ){

        String url = new String ("http://gdlp01.c-wss.com/gds/0/0300004720/02/eosrt3i-eos600d-im2-c-en.pdf");
        String dir = new String ( "./test/");
        String file = new String ( "file.pdf");

        try {
            downloadFileFromUrl( dir , file , url );
        } catch ( IOException e ){
            System.out.println( e.getMessage() );
        }

        return ;

    }

    */

}
