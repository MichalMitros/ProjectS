package client;

import botlogic.Bot;

import java.io.IOException;

/**
 * Created by Patryk on 05.05.2017.
 */
public class RunJarFileAction implements Action {

    private String []command;

    @Override
    public void constructor(Bot bot) {

        setCommand( command );

    }

    @Override
    public void executeAction() {
        try {
            runFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setCommand( String []command ) {

        this.command = new String[ command.length + 3 ];
        this.command[0] = "java";
        this.command[1] = "-jar";

        for( int i = 0 ; i < command.length ; i++ ){
            this.command[i + 2] = command[ i ];
        }

    }

    private void runFile() throws IOException{

        ProcessBuilder processBuilder = new ProcessBuilder( command );

        try{
            processBuilder.start();
        } catch ( IOException e ) {
            throw new IOException( e.getMessage() );
        }

    }

}
