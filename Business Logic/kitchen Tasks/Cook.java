package catering.businesslogic.kitchenTasks;

public class Cook {
    int id;
    String name;
    String lastName;

    public Cook(int id, String name, String lastName){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    public int getId(){
        return id;
    }
    @Override
    public boolean equals(Object o){
        Cook ck = (Cook)o;

        if(this.id == ck.getId()){
            return true;
        }
        return false;
    }

    @Override
    public  String toString(){
        return name.toString() + " " + lastName.toString();
    }

}
