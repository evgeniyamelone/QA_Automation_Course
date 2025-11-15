package ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.annotation.Nullable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String password;
    @NonNull
    private String email;
    @NonNull
    private String day;
    @NonNull
    private String month;
    @NonNull
    private String year;
    @Nullable
    private String streetAddress;
    @Nullable
    private String city;
    @Nullable
    private String state;
    @Nullable
    private String postCode;
    @Nullable
    private String mobilePhone;
    @Nullable
    private String alias;
}

