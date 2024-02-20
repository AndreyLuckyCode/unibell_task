package andrey.code.api.controller;

import andrey.code.api.dto.ClientDTO;
import andrey.code.api.service.ClientService;
import andrey.code.store.ClientEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api")
public class ClientController {

    ClientService clientService;

    public static final String CREATE_CLIENT = "/clients";
    public static final String ADD_CLIENT_CONTACT_INFORMATION = "/clients/{client_id}/add_info";
    public static final String GET_ALL_CLIENTS_LIST = "/clients/all";
    public static final String GET_CLIENT_CONTACT_INFORMATION_LIST = "/clients/{client_id}/info";
    public static final String GET_CLIENT_EMAIL_ADDRESSES_LIST = "/clients/{client_id}/email";
    public static final String GET_CLIENT_PHONE_NUMBER_LIST = "/clients/{client_id}/phone";


    @PostMapping(CREATE_CLIENT)
    public ClientDTO createNewClient(
            @ModelAttribute ClientEntity client){

        return clientService.createNewClient(client);
    }

    @PatchMapping(ADD_CLIENT_CONTACT_INFORMATION)
    public ClientDTO addClientContactInformation(
            @PathVariable(name = "client_id") Long id,
            @ModelAttribute ClientEntity client){

        return clientService.addClientContactInformation(id, client);
    }

    @GetMapping(GET_ALL_CLIENTS_LIST)
    public List<ClientDTO> getAllClientsList(){

        return clientService.getAllClientsList();
    }

    @GetMapping(GET_CLIENT_CONTACT_INFORMATION_LIST)
    public List<String> getClientContactInformationList(
            @PathVariable(name = "client_id") Long id){

        return clientService.getClientContactInformationList(id);
    }

    @GetMapping(GET_CLIENT_EMAIL_ADDRESSES_LIST)
    public List<String> getClientEmailAddressesList(
            @PathVariable(name = "client_id") Long id){

        return clientService.getClientEmailAddressesList(id);
    }

    @GetMapping(GET_CLIENT_PHONE_NUMBER_LIST)
    public List<String> getClientPhoneNumberInformationList(
            @PathVariable(name = "client_id") Long id){

        return clientService.getClientPhoneNumbersList(id);
    }
}
