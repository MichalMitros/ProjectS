package client;

import java.io.IOException;

/**
 * Created by Patryk Cholewa on 05.05.2017.
 */
public class RunJarFileAction implements Action {

    private String []command;
    private String info;

    public RunJarFileAction( String []command ) {

        try {
            setCommand(command);
            info = "RunJarFileAction constructed correctly!";
        } catch ( IllegalArgumentException e ){
            info = e.getMessage();
        }
    }

    @Override
    public void executeAction() {
        try {
            runFile();
            info = "RunJarFileAction executed correctly!";
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

    public void setCommand( String []command ) throws IllegalArgumentException {

        if( !command[0].endsWith( ".jar" ) ){
            throw new IllegalArgumentException( "First argument not jarfile!" );
        }

        this.command = new String[ command.length + 2 ];
        this.command[0] = "java";
        this.command[1] = "-jar";

        for( int i = 0 ; i < command.length ; i++ ){
            this.command[i + 2] = command[ i ];
        }

    }

    private void runFile() throws IOException {

        ProcessBuilder processBuilder = new ProcessBuilder( command );

        try{
            processBuilder.start();
        } catch ( IOException e ) {
            throw new IOException( e.getMessage() );
        }

    }

}
