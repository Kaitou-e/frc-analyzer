public class Alliance {
    private FrcTeam[] teams = new FrcTeam[3];
    private boolean isBlue = true;

    public Alliance(boolean b, FrcTeam cap, FrcTeam p1, FrcTeam p2){
        isBlue = b;
        teams[0] = cap;
        teams[1] = p1;
        teams[2] = p2;
    }

    public Alliance(FrcTeam cap, FrcTeam p1, FrcTeam p2){
        teams[0] = cap;
        teams[1] = p1;
        teams[2] = p2;
    }

    public Alliance(FrcTeam[] ts){
        teams = ts;
    }

    public double getAllianceScore(){
        return teams[0].getOPR() + teams[1].getOPR() + teams[2].getOPR();
    }

    public String getSummary(){
        String res = "";
        res += teams[0].getBasicSummary() + "\n";
        res += teams[1].getBasicSummary() + "\n";
        res += teams[2].getBasicSummary() + "\n";
        return res;
    }
}
