package FansBook;

public class User {
    // data part:
    private String name;
    private String password;
    private String personInfo;

    // friend list:
    private FriendsList friendsList;

    // link part:
    private User nextUser;

    public User() {
        new User("unknown", "0000", "No Info");
    }

    public User(String name, String password, String info) {
        this.name = name;
        this.password = password;
        this.personInfo = info;
        this.nextUser = null;
        this.friendsList = new FriendsList();
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

    public FriendsList getFriendsList() {
        return friendsList;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password=" + password +
                ", personInfo='" + personInfo + '\'' +
                ", nextUser=" + nextUser +
                '}';
    }

    public void printUser() {
        System.out.println("User: " + name);
        System.out.println("Information: " + personInfo);
    }

    public void addFriend(User friend) {
        // FIXME: this method should add User by passing a string param(user name),
        //  then add that user from UserDB, if no such the user name found, throw exception.
        this.friendsList.add(friend);
    }

    public boolean deleteFriend(String name) {
        return this.friendsList.delete(name);
    }
}
