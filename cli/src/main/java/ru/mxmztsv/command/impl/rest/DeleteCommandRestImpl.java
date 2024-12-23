package ru.mxmztsv.command.impl.rest;

import ru.mxmztsv.app.soap.ClientServiceException_Exception;
import ru.mxmztsv.client.WebClientImpl;
import ru.mxmztsv.client.exception.ClientException;
import ru.mxmztsv.command.Command;
import ru.mxmztsv.command.CommandHandler;
import ru.mxmztsv.command.Key;
import ru.mxmztsv.command.Protocol;
import ru.mxmztsv.command.mapper.ClientMapper;
import ru.mxmztsv.model.Client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Map;

public class DeleteCommandRestImpl implements CommandHandler {

    private final WebClientImpl webClient;

    public DeleteCommandRestImpl(WebClientImpl webClient) {
        this.webClient = webClient;
    }

    @Override
    public void execute(Map<Key, String> params) throws ClientServiceException_Exception, ParseException, URISyntaxException, IOException, InterruptedException, ClientException {
        Client client = ClientMapper.mapKeysToClient(params);
        webClient.deleteClient(client.getId());
    }

    @Override
    public Command getName() {
        return Command.DELETE;
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.REST;
    }
}
