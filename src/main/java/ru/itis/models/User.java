package ru.itis.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class User {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
    public User(Long id, String password){
        this.password = password;
        this.id = id;
    }
}

//    public String getEmail() { return email; }
//    public Long getId() { return id; }
//    public String getName() {
//        return name;
//    }
//    public String getPassword() {
//        return password;
//    }
//    public String getSurname() {
//        return surname;
//    }

//    public User(Long id, String email, String name,String surname,String password){
//        this.id = id;
//        this.email = email;
//        this.name = name;
//        this.surname = surname;
//        this.password = password;
//    }
