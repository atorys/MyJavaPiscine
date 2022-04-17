package Day01.ex04_BusinessLogic;

public class UserArrayList implements UsersList {

    private User[]      users;
    private int         capacity;
    private int         size;


    public UserArrayList() {
        this.capacity = 10;
        this.size = 0;
        this.users = new User[capacity];
    }

    @Override
    public int      getNumberUsers() { return size; }

    private void    fill(User[] oldArray, int oldSize) {
        for (int i = 0; i < oldSize; i++)
            this.users[i] = oldArray[i];
    }

    @Override
    public void     addUser(User newUser) {
        try {
            this.getByID(newUser.getIdentifier());
        }
        catch (UserNotFoundException e) {
            if (capacity < size + 1) {
                capacity *= 2;
                User[]  tmp = users;
                users = new User[capacity];
                fill(tmp, size);
            }
            users[size++] = newUser;
        }
    }

    @Override
    public User getByID(Integer id) throws UserNotFoundException {
        for (int i = 0; i < size; i++)
            if (users[i].getIdentifier().equals(id))
                return users[i];
        throw new UserNotFoundException();
    }

    @Override
    public User getByIndex(Integer index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException("\033[31mInvalid index\n\033[0m");
        return users[index];
    }


    public void     showList() {
        for (int i = 0; i < size; i++) {
            System.out.printf("%d) ", i);
            users[i].displayUser();
        }
    }

    public void     showListHead(int first) {
        for (int i = 0; i < size && i < first; i++) {
            System.out.printf("%d) ", i);
            users[i].displayUser();
        }
    }
    public void     showListTail(int last) {
        int first = (size - last < 0 ? 0 : size - last);
        for (int i = first; i < size && last-- > 0; i++) {
            System.out.printf("%d) ", i);
            users[i].displayUser();
        }
    }

}
