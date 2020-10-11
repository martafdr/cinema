package by.itacademy.gomel.model;

public class User {

    private int idUser;
    private String login;
    private String password;
    private TypeUser typeUser;

    public User() {
    }

    public User(int idUser) {
        this.idUser = idUser;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(int idUser, String login, String password, TypeUser typeUser) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.typeUser = typeUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }


    @Override
    public String toString() {
        return "User with ID " + idUser +
                ": login '" + login + '\'' +
                ", password '" + password + '\'' +
                ", type " + typeUser;
    }
}
