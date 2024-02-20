package andrey.code.api.service;

import andrey.code.api.dto.ClientDTO;
import andrey.code.store.ClientEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ClientService {

    public ClientDTO createNewClient (
            @ModelAttribute ClientEntity client);

    public ClientDTO addClientContactInformation (
            @PathVariable(name = "client_id") Long id,
            @ModelAttribute ClientEntity client);

    public List<ClientDTO> getAllClientsList();

    public List<String> getClientContactInformationList(
            @PathVariable(name = "client_id") Long id);

    public List<String> getClientEmailAddressesList(
            @PathVariable(name = "client_id") Long id);

    public List<String> getClientPhoneNumbersList(
            @PathVariable(name = "client_id") Long id);
}
