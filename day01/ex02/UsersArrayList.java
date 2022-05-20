class UserNotFoundException extends RuntimeException {}

public class UsersArrayList  implements  UsersList{

    private User []Users;
    private  Integer count;
    private final  Integer DEFAULT_SIZE = 10;

    public  UsersArrayList(){
        count = 0;
        Users = new User[DEFAULT_SIZE];
    }

    @Override
    public void add(User user) {
        if (Users.length == count) {
            User []newUsers = new User[count + count];
            for(int i = 0; i < Users.length; i++) {
                newUsers[i]= Users[i];
            }
            Users = newUsers;
        }
        Users[count] = user;
        count++;
    }

    @Override
    public User getById(Integer id) {
        for(int i = 0; i < Users.length; i++) {
           if (Users[i].getIdentifier() == id){
               return Users[i];
           }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getByIndex(Integer id) {
       if (id > 0 && id < count) {
           return Users[id];
       }
        throw new UserNotFoundException();
    }

    @Override
    public Integer getCount() {
        return count;
    }
}
