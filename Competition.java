import java.util.ArrayList;

public class Competition {
    private ArrayList<FrcTeam> teams;
    public Competition(){

    }

    public Competition(String filename){
        this.loadInfoCSV(filename);
        this.loadPerformanceCSV(filename);
    }

    public void loadInfoCSV(String filename){

    }
    public void loadPerformanceCSV(String filename){

    }
}
