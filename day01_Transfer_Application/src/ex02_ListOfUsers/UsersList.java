package ex02_ListOfUsers;

public interface UsersList {

    void    addUser(User newUser);
    User    getByID(Integer id)  throws UserNotFoundException;
    User    getByIndex(Integer index) throws ArrayIndexOutOfBoundsException;
    int     getNumberUsers();
}
