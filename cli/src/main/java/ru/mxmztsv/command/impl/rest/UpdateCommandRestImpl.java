package ru.mxmztsv.command.impl.rest;


import ru.mxmztsv.app.soap.ClientServiceException_Exception;
import ru.mxmztsv.client.WebClientImpl;
import ru.mxmztsv.client.exception.ClientException;
import ru.mxmztsv.client.model.ClientResponseDto;
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

public class UpdateCommandRestImpl implements CommandHandler {

    private final WebClientImpl webClient;

    public UpdateCommandRestImpl(WebClientImpl webClient) {
        this.webClient = webClient;
    }

    @Override
    public void execute(Map<Key, String> params) throws ClientServiceException_Exception, ParseException, URISyntaxException, IOException, InterruptedException, ClientException {
        Client client = ClientMapper.mapKeysToClient(params);
        ClientResponseDto response =
                webClient.updateClient(client.getId(), ClientMapper.mapToRestRequestCreate(client));
        System.out.println(ClientMapper.mapToString(response));
    }

    @Override
    public Command getName() {
        return Command.UPDATE;
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.REST;
    }
}
