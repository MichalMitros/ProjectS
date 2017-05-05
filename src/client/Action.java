package client;

import botlogic.Bot;

/**
 * Created by kubehe on 30-04-2017.
 */

public interface Action {
    void getBotObject(Bot bot); // to send info messages to the server and in case some other function from Bot is required
    void executeAction();
    void sendInfo(); // to send info when Action has ben

}
