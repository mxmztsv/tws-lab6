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

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Map;

public class CreateCommandRestImpl implements CommandHandler {

    private final WebClientImpl client;

    public CreateCommandRestImpl(WebClientImpl client) {
        this.client = client;
    }

    @Override
    public void execute(Map<Key, String> params) throws ClientServiceException_Exception, ParseException, URISyntaxException, IOException, InterruptedException, ClientException {
        ClientResponseDto clientResponse =
                client.create(ClientMapper.mapToRestRequestCreate(ClientMapper.mapKeysToClient(params)));
        System.out.println(ClientMapper.mapToString(clientResponse));
    }

    @Override
    public Command getName() {
        return Command.CREATE;
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.REST;
    }
}
