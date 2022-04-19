package Day01.ex04_BusinessLogic;

public class UserIdsGenerator {

    private static final UserIdsGenerator   instance;
    private Integer                         lastGeneratedId;

    static          { instance = new UserIdsGenerator(1); }

    private         UserIdsGenerator(Integer firstId)   { this.lastGeneratedId = firstId; }
    public static   UserIdsGenerator  getInstance()     { return instance; }
    public          Integer           generateId()      { return this.lastGeneratedId++; }

}
