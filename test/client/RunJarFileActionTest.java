package client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Patryk on 08.05.2017.
 */
class RunJarFileActionTest {

    @AfterEach
    void tearDown() {

        File tmpfile;
        tmpfile = new File( "./test/testFiles/client.run.JarFile.tmpFile" );
        tmpfile.delete();

    }

    @Test
    void sendInfoTestConstructorNotJar(){

        RunJarFileAction action = new RunJarFileAction();
        String []command = { "./test/testFiles/client.run.JarFile.tmpFile" };

        action.constructor( command );
        assertFalse( action.sendInfo().contains( "correctly" ));

    }

    @Test
    void sendInfoTestConstructorCorrect(){

        RunJarFileAction action = new RunJarFileAction();
        String []command = { "./test/testFiles/client.runJarFile.TestFile.jar" };

        action.constructor( command );
        assertTrue( action.sendInfo().contains( "correctly" ));

    }

    @Test
    synchronized void  executeActionTest() {

        String []command =
                { "./test/testFiles/client.runJarFile.TestFile.jar" , "./test/testFiles/client.run.JarFile.tmpFile" };
        RunJarFileAction action = new RunJarFileAction();
        action.constructor( command );

        //Test program creates file on given path.
        action.executeAction();

        try {
            Thread.sleep(2000);
        } catch( InterruptedException e ){
            fail( e.getMessage() );
        }

        assertTrue( new File( "./test/testFiles/client.run.JarFile.tmpFile").exists() );
        assertTrue( action.sendInfo().contains( "correctly" ));

    }

}