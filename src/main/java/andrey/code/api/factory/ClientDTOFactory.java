package andrey.code.api.factory;

import andrey.code.api.dto.ClientDTO;
import andrey.code.store.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientDTOFactory {

    public ClientDTO createClientDTO(ClientEntity entity){

        return ClientDTO
                .builder()
                .name(entity.getName())
                .emailAddresses(entity.getEmailAddresses())
                .phoneNumbers(entity.getPhoneNumbers())
                .build();
    }
}
