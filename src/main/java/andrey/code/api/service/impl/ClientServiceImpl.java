package andrey.code.api.service.impl;

import andrey.code.api.dto.ClientDTO;
import andrey.code.api.exceptions.BadRequestException;
import andrey.code.api.exceptions.NotFoundException;
import andrey.code.api.factory.ClientDTOFactory;
import andrey.code.api.service.ClientService;
import andrey.code.store.ClientEntity;
import andrey.code.store.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;
    ClientDTOFactory clientDTOFactory;


    @Override
    @Transactional
    public ClientDTO createNewClient(
            @ModelAttribute ClientEntity client) {

        if(client.getName() == null || client.getName().trim().isEmpty()){
            throw new BadRequestException("Name field can't be empty");
        }

        clientRepository.saveAndFlush(client);

        return clientDTOFactory.createClientDTO(client);
    }


    @Override
    @Transactional
    public ClientDTO addClientContactInformation(
            @PathVariable(name = "client_id") Long id,
            @ModelAttribute ClientEntity client) {

        ClientEntity clientEntity = clientRepository.findById(id).orElseThrow(()
                -> new NotFoundException("Client with this id was not found"));

        if(!client.getEmailAddresses().isEmpty()){
            clientEntity.getEmailAddresses().addAll(client.getEmailAddresses());
        }
        if(!client.getPhoneNumbers().isEmpty()){
            clientEntity.getPhoneNumbers().addAll(client.getPhoneNumbers());
        }

        clientRepository.saveAndFlush(clientEntity);

        return clientDTOFactory.createClientDTO(clientEntity);
    }


    @Override
    @Transactional
    public List<ClientDTO> getAllClientsList() {

        List<ClientEntity> clients = clientRepository.findAll();

        if(clients.isEmpty()){
            throw new NotFoundException("Clients list is empty");
        }

        return clients.stream()
                .map(clientDTOFactory::createClientDTO)
                .toList();
    }


    @Override
    @Transactional
    public List<String> getClientContactInformationList(
            @PathVariable(name = "client_id") Long id) {

        Optional<ClientEntity> clientOptional = clientRepository.findById(id);

        if (clientOptional.isEmpty()) {
            throw new NotFoundException("Client with id " + id + " not found");
        }

        ClientEntity clientEntity = clientOptional.get();

        List<String> contactInformationList = new ArrayList<>();
        contactInformationList.addAll(clientEntity.getEmailAddresses());
        contactInformationList.addAll(clientEntity.getPhoneNumbers());

        return contactInformationList;
    }


    @Override
    @Transactional
    public List<String> getClientEmailAddressesList(
            @PathVariable(name = "client_id") Long id) {

        Optional<ClientEntity> clientOptional = clientRepository.findById(id);

        if (clientOptional.isEmpty()) {
            throw new NotFoundException("Client with id " + id + " not found");
        }

        ClientEntity clientEntity = clientOptional.get();

        return new ArrayList<>(clientEntity.getEmailAddresses());
    }


    @Override
    @Transactional
    public List<String> getClientPhoneNumbersList(
            @PathVariable(name = "client_id") Long id) {

        Optional<ClientEntity> clientOptional = clientRepository.findById(id);

        if (clientOptional.isEmpty()) {
            throw new NotFoundException("Client with id " + id + " not found");
        }

        ClientEntity clientEntity = clientOptional.get();

        return new ArrayList<>(clientEntity.getPhoneNumbers());
    }
}
