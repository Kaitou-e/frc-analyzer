public class Team{
    protected int teamNumber;
    protected String teamName;
    protected String city;
    protected String stateProv;
    protected String country;

    public Team(int n, String name, String c, String sP, String coun){
        teamNumber = n;
        teamName = name;
        city = c;
        stateProv = sP;
        country = coun;
    }

    public int getTeamNumber(){return teamNumber;}
    public String getTeamName(){return teamName;}
    public String getCity(){return city;}
    public String getStateProv(){return stateProv;}
    public String getCountry(){return country;}

    public void setTeamNumber(int n){teamNumber = n;}
    public void setTeamName(String s){teamName = s;}
    public void setCity(String s){city = s;}
    public void setStateProv(String s){stateProv = s;}
    public void setCountry(String s){country = s;}
    
    public String getBasicSummary(){
        return ("Team " + teamNumber + ", " + teamName);
    }

    public String getFullSummary(){
        String res = this.getBasicSummary();
        res += "\nFrom " + city + ", " + ", " + stateProv + ", " + country;
        return res;
    }
}