package FansBook;

public class User {
    private String name;
    private int idNum;
    private String password;
    private String personInfo;
    private FriendsList friendsList;
    private User nextUser;

    public User() {
        new User("unknown", "0000", "No Info");
    }

    public User(String name, String password, String info) {
        this.name = name;
        this.idNum = generateID();
        this.password = password;
        this.personInfo = info;
        this.nextUser = null;
    }

    public static int generateID() {
        int id = (int) (Math.random() * 100000) + 100000;
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(String personInfo) {
        this.personInfo = personInfo;
    }

    public User getNextUser() {
        return nextUser;
    }

    public void setNextUser(User nextUser) {
        this.nextUser = nextUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", idNum=" + idNum +
                ", password=" + password +
                ", personInfo='" + personInfo + '\'' +
                ", nextUser=" + nextUser +
                '}';
    }

    public void printUser() {
        System.out.println("User: " + name);
        System.out.println("Information: " + personInfo);
    }

    public void addFirend(User friend){

    }
}
