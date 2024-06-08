package ec.edu.uce.ProyectoJuego.model;

public class User extends Hero{
    private long id;
    private String name;
    private String password;

    public User(){}

    public User(String name, String password) {
        this.name = name;
        this.password = password;

    }

    public User (long id,String name, String password,int life,int score)
    {    super( life,score);
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
