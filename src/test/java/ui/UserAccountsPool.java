package ui;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAccountsPool {
    Map<String, UserAccount> userAccountRegistrationForms;

    public void deserialize(String filePath) {
        userAccountRegistrationForms = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        try {
            UserAccount registrationForm = objectMapper.readValue(file, UserAccount.class);
            userAccountRegistrationForms.put(filePath, registrationForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serialize(String filePath, UserAccount userAccount) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserAccount registrationForm = new UserAccount();
            registrationForm.setFirstName(userAccount.getFirstName());
            registrationForm.setLastName(userAccount.getLastName());
            registrationForm.setEmail(userAccount.getEmail());
            registrationForm.setPassword(userAccount.getPassword());
            registrationForm.setDay(userAccount.getDay());
            registrationForm.setMonth(userAccount.getMonth());
            registrationForm.setYear(userAccount.getYear());
            registrationForm.setStreetAddress(userAccount.getStreetAddress());
            registrationForm.setCity(userAccount.getCity());
            registrationForm.setState(userAccount.getState());
            registrationForm.setPostCode(userAccount.getPostCode());
            registrationForm.setMobilePhone(userAccount.getMobilePhone());
            registrationForm.setAlias(userAccount.getAlias());
            objectMapper.writeValue(new FileOutputStream(filePath), registrationForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserAccount getUser(String filePath) {
        return userAccountRegistrationForms.get(filePath);
    }
}
