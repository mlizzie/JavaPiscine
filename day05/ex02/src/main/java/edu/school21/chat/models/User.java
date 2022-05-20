package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long userID;
    private String login, password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> activeChatroom;

    public User(Long userId, String login, String password, List<Chatroom> createdRooms, List<Chatroom> activeChatroom) {
        this.userID = userId;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.activeChatroom = activeChatroom;
    }

    public void setActiveChatroom(List<Chatroom> activeChatroom) {
        this.activeChatroom = activeChatroom;
    }

    public void setCreatedRoms(List<Chatroom> craeteRoms) {
        this.createdRooms = craeteRoms;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getUserID() {
        return userID;
    }

    public List<Chatroom> getActiveChatroom() {
        return activeChatroom;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userID +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + createdRooms +
                ", activeChatroom=" + activeChatroom +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return userID.equals(user.userID) && login.equals(user.login) && password.equals(user.password) && createdRooms.equals(user.createdRooms) && activeChatroom.equals(user.activeChatroom);
    }
    @Override
    public int hashCode() {
        return Objects.hash(userID, login, password, createdRooms, activeChatroom);
    }
}

