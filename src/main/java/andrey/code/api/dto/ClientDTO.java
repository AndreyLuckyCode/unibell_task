package andrey.code.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientDTO {

    String name;

    List<String> emailAddresses;

    List<String> phoneNumbers;
}
