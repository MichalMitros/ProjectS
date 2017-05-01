package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 * Created by kubehe on 30-04-2017.
 */

public interface Action {
    void constructor(BufferedReader reader, BufferedWriter writer, String channel, String login); // test in out
    void executeAction();

}
