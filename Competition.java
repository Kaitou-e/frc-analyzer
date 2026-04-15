import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class Competition {
    private ArrayList<FrcTeam> teams;
    private String compName;
    public Competition(String cName){
        compName = cName;
    }

    public Competition(String cName, String filename){
        compName = cName;
        this.loadInfoCSV(filename);
        this.loadPerformanceCSV(filename);
    }

    public void loadInfoCSV(String filename){
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(",");
                if (s[0].equals("team_number")) continue;
                int teamN = Integer.parseInt(s[0]);
                boolean flag = false;
                for (FrcTeam t : teams){
                    if (t.getTeamNumber() == teamN){
                        t.setTeamName(s[1]);
                        t.setCity(s[2]);
                        t.setStateProv(s[3]);
                        t.setCountry(s[4]);
                        flag = true;
                        break;
                    }
                }
                if (!flag) teams.add(new FrcTeam(teamN,s[1],s[2],s[3],s[4],0,0,0,0,0,0,0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPerformanceCSV(String filename){
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(",");
                if (s[0].equals("team_number")) continue;
                int teamN = Integer.parseInt(s[0]);
                boolean flag = false;
                for (FrcTeam t : teams){
                    if (t.getTeamNumber() == teamN){
                        t.setOPR(Double.parseDouble(s[1]));
                        t.setTotalPoints(Double.parseDouble(s[25]));
                        t.setTotalAutoPoints(Double.parseDouble(s[15]));
                        t.setTotalTeleopPoints(Double.parseDouble(s[16]));
                        t.setEndGameTowerPoints(Double.parseDouble(s[17]));
                        t.setFoulPoints(Double.parseDouble(s[23]));
                        t.setRP(Double.parseDouble(s[24]));
                        flag = true;
                        break;
                    }
                }
                if (!flag) teams.add(new FrcTeam(teamN,"","","","",
                    Double.parseDouble(s[1]), Double.parseDouble(s[25]),
                    Double.parseDouble(s[15]), Double.parseDouble(s[16]),
                    Double.parseDouble(s[17]), Double.parseDouble(s[23]),
                    Double.parseDouble(s[24])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FrcTeam findTeam(int teamN){
        for (FrcTeam t : teams){
            if (t.getTeamNumber() == teamN){
                return t;
            }
        }
        return null;
    }

    public void printAllTeams(){
        System.out.println("-------- All teams at " + compName + " --------");
        for (FrcTeam t : teams){
            System.out.println(t.getTeamNumber() + ": " + t.getTeamName());
        }
    }

    
}
