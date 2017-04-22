package client;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.misc.IOUtils;


import java.io.*;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Created by Patryk Cholewa on 17.04.2017.
 */

class ClientUtilsTest {

    @AfterEach
    void tearDown() {

        File tmpfile;
        tmpfile = new File( "./test/testFiles/client.downloadFromUrl.tmpFile.txt" );
        tmpfile.delete();
        tmpfile = new File( "./test/testFiles/client.run.JarFile.tmpFile" );
        tmpfile.delete();
        tmpfile = new File( "./test/testFiles/tmpFile" );
        tmpfile.delete();
    }

    @Test
    synchronized void downloadFileFromUrlTest() {

        String urlString = new String ("http://websitetips.com/articles/copy/lorem/ipsum.txt");
        String dirPathString = new String ( "./test/testFiles");
        String testfilePathString = new String ( "./test/testFiles/client.downloadFromUrl.testfile.txt");
        String tmpfileNameString = new String ( "client.downloadFromUrl.tmpFile.txt");
        File tmpfile = new File( dirPathString + "/" + tmpfileNameString );
        File testfile = new File( testfilePathString );

        try {
            ClientUtils.downloadFileFromUrl( dirPathString , tmpfileNameString , urlString );
        } catch ( IOException e ){
            fail( e.getMessage() );
        }

          try {

            FileReader tmpFileReader = new FileReader( tmpfile );
            FileReader testFileReader = new FileReader( testfile );
            BufferedReader tmpFileBufferedReader = new BufferedReader( tmpFileReader );
            BufferedReader testFileBufferedReader = new BufferedReader( testFileReader );
            char []buf = new char[2048];
            int testChar;
            int tmpChar;


            while( ( testChar = testFileBufferedReader.read( buf , 0 , 2048 ) ) != -1 ) {
                if( ( tmpChar = tmpFileBufferedReader.read( buf , 0 , 2048 ) ) == -1 ){
                    fail( "Files have different lengths!");
                } else {
                    if( tmpChar != testChar ){
                        fail( "Files do not match each other!");
                    }
                }
            }

            if( ( tmpChar = tmpFileBufferedReader.read( buf ,0 , 2048) ) != -1 ){
                fail( "Files have different lengths!");
            }

            testFileBufferedReader.close();
            testFileReader.close();
            tmpFileBufferedReader.close();
            tmpFileReader.close();

        } catch ( IOException e ){
            fail( "Failed at reading downloaded testFiles!");
        }

        return ;

    }

    @Test
    synchronized void  runJarFileTestWithArgs() {

        String []args = new String[1];
        args[0] = "./test/testFiles/client.run.JarFile.tmpFile";
        Process process;

        try {
            //Test program creates file on given path with.
            process = ClientUtils.runJarFile("./test/testFiles/client.runJarFile.TestFile.jar", args);

            try {
                Thread.sleep(2000);
            } catch( InterruptedException e ){
                fail( e.getMessage() );
            }

            if( ( new File( "./test/testFiles/client.run.JarFile.tmpFile").exists() ) == false ){
                fail( "Test program did not work properly!" );
            }

        } catch( IOException e ) {
            fail( e.getMessage() );
        }



    }

    @Test
    synchronized void runJarFileTestWithoutArgs(){

        Process process;

        try {
            //Test program creates file on given path with.
            process = ClientUtils.runJarFile("./test/testFiles/client.runJarFile.TestFile.jar" );

            try {
                Thread.sleep(2000);
            } catch( InterruptedException e ){
                fail( e.getMessage() );
            }

            if( ( new File( "./test/testFiles/tmpFile").exists() ) == false ){
                fail( "Test program did not work properly!" );
            }

        } catch( IOException e ) {
            fail( e.getMessage() );
        }

    }

}