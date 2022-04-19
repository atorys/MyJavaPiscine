package Day01.ex05_Menu;

public interface UsersList {

    void    addUser(User newUser);
    User    getByID(Integer id)  throws UserNotFoundException;
    User    getByIndex(Integer index) throws ArrayIndexOutOfBoundsException;
    int     getNumberUsers();
}
