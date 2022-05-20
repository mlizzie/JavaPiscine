public class UserIdsGenerator {

    private  Integer ID;
    private static UserIdsGenerator userIdsGenerator;
    private UserIdsGenerator(){
        ID = 0;
    }
    public static UserIdsGenerator getInstance() {
        if (userIdsGenerator == null){
            userIdsGenerator = new UserIdsGenerator();
        }
        return userIdsGenerator ;
    }

    public  Integer generateId() {
        return ID++;
    }
}
