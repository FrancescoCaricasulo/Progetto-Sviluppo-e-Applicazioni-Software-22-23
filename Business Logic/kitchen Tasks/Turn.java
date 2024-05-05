package catering.businesslogic.kitchenTasks;
import java.time.LocalDateTime;

public class Turn {
    int id;
    LocalDateTime start;
    LocalDateTime end;

    public Turn(int id, LocalDateTime start, LocalDateTime end){
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o){
        Turn t = (Turn)o;
        if (this.getId() == t.getId())
            return true;
        return false;
    }

    @Override
    public String toString(){
        return start.toString() + " " + end.toString();
    }
}
