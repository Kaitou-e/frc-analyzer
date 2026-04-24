import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;

public class Competition {
    private ArrayList<FrcTeam> teams;
    private String compName;
    public Competition(String cName){
        teams = new ArrayList<FrcTeam>();
        compName = cName;
    }

    public Competition(String cName, String filename){
        teams = new ArrayList<FrcTeam>();
        compName = cName;
        this.loadInfoCSV("data/"+filename+"_team_list.csv");
        this.loadPerformanceCSV("data/"+filename+"_coprs.csv");
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
        sortByTeamNumber();
        System.out.println("-------- All teams at " + compName + " --------");
        for (FrcTeam t : teams){
            System.out.println("Team " + t.getTeamNumber() + ": " + t.getTeamName());
        }
    }

    public void printTopTeamsOPR(int n){
        sortByOPR();
        n = Math.min(n, teams.size());
        System.out.println("-------- Top " + n + " teams at " + compName + " by OPR --------");
        for (int i = teams.size()-1; i >= teams.size()-n; i--){
            System.out.println((teams.size()-i) + ". Team " + teams.get(i).getTeamNumber() + ", OPR: " + teams.get(i).getOPR());
        }
    }

    public void printTopTeamsOPR(){
        printTopTeamsOPR(10);
    }

    public void printTopTeamsTotScore(int n){
        sortByTotScore();
        n = Math.min(n, teams.size());
        System.out.println("-------- Top " + n + " teams at " + compName + " by total points --------");
        for (int i = teams.size()-1; i >= teams.size()-n; i--){
            System.out.println((teams.size()-i) + ". Team " + teams.get(i).getTeamNumber() + ", Total Points: " + teams.get(i).getTotalPoints());
        }
    }

    public void printTopTeamsTotScore(){
        printTopTeamsTotScore(10);
    }

    public void sortByOPR(){
        Collections.sort(teams, (o1, o2) -> {
            FrcTeam a = (FrcTeam) o1;
            FrcTeam b = (FrcTeam) o2;
            if (a.getOPR() < b.getOPR()) return -1;
            if (a.getOPR() > b.getOPR()) return 1;
            return 0;
        });
    }

    public void sortByTotScore(){
        Collections.sort(teams, (o1, o2) -> {
            FrcTeam a = (FrcTeam) o1;
            FrcTeam b = (FrcTeam) o2;
            if (a.getTotalPoints() < b.getTotalPoints()) return -1;
            if (a.getTotalPoints() > b.getTotalPoints()) return 1;
            return 0;
        });
    }

    public void sortByTeamNumber(){
        Collections.sort(teams, (o1, o2) -> {
            FrcTeam a = (FrcTeam) o1;
            FrcTeam b = (FrcTeam) o2;
            if (a.getTeamNumber() < b.getTeamNumber()) return -1;
            if (a.getTeamNumber() > b.getTeamNumber()) return 1;
            return 0;
        });
    }
}
