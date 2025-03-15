package com.AlGelsin.user_service.dto;

public class CreateUserRequestDto {

    private Long authId;
    private String name;
    private String surname;
    private String email;

    public CreateUserRequestDto(){

    }

    public CreateUserRequestDto(Long authId, String name, String surname, String email){
        this.authId = authId;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Long getAuthId(){
        return authId;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getEmail(){
        return email;
    }
}
