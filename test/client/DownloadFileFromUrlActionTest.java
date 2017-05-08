package client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Patryk Cholewa on 08.05.2017.
 */
class DownloadFileFromUrlActionTest {

    @AfterEach
    void tearDown() {

        File tmpfile;
        tmpfile = new File( "./test/testFiles/client.downloadFromUrl.tmpFile.txt" );
        tmpfile.delete();

    }

    @Test
    void sendInfoTest() {

        DownloadFileFromUrlAction action = new DownloadFileFromUrlAction(
                "client.downloadFromUrl.tmpFile.txt" ,
                "./test/testFiles" ,
                "http://websitetips.com/articles/copy/lorem/ipsum.txt" );
        assertTrue( action.getInfo().contains( "correctly" ));

    }

    @Test
    synchronized void executeActionTest() {

        System.out.println( "executeActionTest results depend on internet connection.");

        DownloadFileFromUrlAction action = new DownloadFileFromUrlAction(
                "client.downloadFromUrl.tmpFile.txt" ,
                "./test/testFiles" ,
                "http://websitetips.com/articles/copy/lorem/ipsum.txt" );

        action.executeAction();

        File tmpfile = new File( "./test/testFiles/client.downloadFromUrl.tmpFile.txt" );
        File testfile = new File( "./test/testFiles/client.downloadFromUrl.TestFile.txt" );

        try {
            FileReader tmpFileReader = new FileReader(tmpfile);
            FileReader testFileReader = new FileReader(testfile);
            BufferedReader tmpFileBufferedReader = new BufferedReader(tmpFileReader);
            BufferedReader testFileBufferedReader = new BufferedReader(testFileReader);
            char[] buf = new char[2048];
            int testChar;
            int tmpChar;

            while ((testChar = testFileBufferedReader.read(buf, 0, 2048)) != -1) {

                assertNotEquals( -1 , tmpChar = tmpFileBufferedReader.read(buf, 0, 2048), " Files have different length!");
                assertEquals(testChar , tmpChar , "Files do not match each other!");

            }

            assertEquals( -1 , tmpFileBufferedReader.read(buf, 0, 2048), "Files have different lengths!");

            testFileBufferedReader.close();
            testFileReader.close();
            tmpFileBufferedReader.close();
            tmpFileReader.close();

        } catch ( IOException e ){
            fail( e.getMessage() );
        }

        assertTrue( action.getInfo().contains( "correctly" ) );

    }

}