package FansBook;


public class User {
    // data part:
    private String name;
    private String password;
    private String personInfo;
    private String contactInfo;

    // friend list:
    private FriendsList friendsList;

    private EdgeList connections;
    // link part:
    private User nextUser;

    public User() {

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.personInfo = "Unknown user.";
        this.contactInfo = "Unknown contact.";
        this.nextUser = null;
        this.friendsList = new FriendsList();
        this.connections = new EdgeList();
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

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
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
                ", contactInfo= '" + contactInfo +
                '}';
    }

    public void printUser() {
        System.out.println("\nUser: " + name);
        System.out.println("Information: " + personInfo);
        System.out.println("Contact: " + contactInfo);
    }

    public void addFriend(User friend) {
        this.friendsList.add(friend);
    }

    public boolean deleteFriend(String name) {
        return this.friendsList.delete(name);
    }

    public void printFriendList() {
        this.friendsList.printList();
    }

    public void printFriend(String name) {
        this.friendsList.printItem(name);
    }

    public void addConnection(Edge newEdge) {
        this.connections.add(newEdge);
    }

    public void removeConnection(User begin, User end) {
        connections.remove(begin, end);
    }

    public void printConnection() {
        this.connections.print();
    }

    public int getFriendsCount(){
        return this.friendsList.getSize();
    }
}
