public class FrcTeam extends Team{
    private double opr;
    private double totalPoints;
    private double totalAutoPoints;
    private double totalTeleopPoints;
    private double endGameTowerPoints;
    private double foulPoints;
    private double rp;

    public FrcTeam(int n, String name, String c, String sP, String coun, 
        double o, double tP, double tAP, double tTP, double eTP, double fP, double rankp){
        super(n, name, c, sP, coun);
        opr = o;
        totalPoints = tP;
        totalAutoPoints = tAP;
        totalTeleopPoints = tTP;
        endGameTowerPoints = eTP;
        foulPoints = fP;
        rp = rankp;
    }

    public double getOPR(){return opr;}
    public double getTotalPoints(){return totalPoints;}
    public double getTotalAutoPoints(){return totalAutoPoints;}
    public double getTotalTeleopPoints(){return totalTeleopPoints;}
    public double getEndGameTowerPoints(){return endGameTowerPoints;}
    public double getFoulPoints(){return foulPoints;}
    public double getRP(){return rp;}

    public void setTotalPoints(double d){totalPoints = d;}
    public void setOPR(double d){opr = d;}
    public void setTotalAutoPoints(double d){totalAutoPoints = d;}
    public void setTotalTeleopPoints(double d){totalTeleopPoints = d;}
    public void setEndGameTowerPoints(double d){endGameTowerPoints = d;}
    public void setFoulPoints(double d){foulPoints = d;}
    public void setRP(double d){rp = d;}

    public double getStrengthScore(){
        return 0;
    }

    public double getAdjustedScore(){
        return totalPoints-foulPoints;
    }

    public String getFullSummary(){
        String res = super.getFullSummary();
        return res;
    }
}
