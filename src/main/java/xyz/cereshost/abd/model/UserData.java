package xyz.cereshost.abd.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class UserData {

    private final String dni;
    private final String name;
    private final String address;
    private final int telephone;

}
