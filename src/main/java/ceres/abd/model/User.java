package ceres.abd.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class User {

    private final int dni;
    private final String name;
    private final String email;
    private final int telephone;

}
