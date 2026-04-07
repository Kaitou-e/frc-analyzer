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

    public double getStrengthScore(){
        return 0;
    }

    public double getAdjustedScore(){
        return totalPoints-foulPoints;
    }
}
