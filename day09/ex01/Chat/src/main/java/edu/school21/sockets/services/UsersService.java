package edu.school21.sockets.services;

public interface UsersService  {
    String signUp(String name, String password);
    Boolean signIn(String name, String password);
}
