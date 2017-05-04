package client;

import botlogic.Bot;

/**
 * Created by kubehe on 30-04-2017.
 */

public interface Action {
    void constructor(Bot bot);
    void executeAction();

}
