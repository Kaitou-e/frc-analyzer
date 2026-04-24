import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Competition competition = new Competition("");

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("==== FRC ANALYZER ====");

        while (running) {
            printMainMenu();
            int choice = getIntInput("Select an option: ");

            switch (choice) {
                case 1:
                    loadDataMenu();
                    break;
                case 2:
                    teamLookupMenu();
                    break;
                case 3:
                    rankingsMenu();
                    break;
                case 4:
                    allianceMenu();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1-5.");
            }
        }

        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("==== MAIN MENU ====");
        System.out.println("1. Load Data");
        System.out.println("2. Team Lookup");
        System.out.println("3. Rankings");
        System.out.println("4. Alliance Tools");
        System.out.println("5. Exit");
    }

    private static void loadDataMenu() {
        boolean back = false;

        while (!back) {
            System.out.println();
            System.out.println("---- LOAD DATA ----");
            System.out.println("1. Load CSVs by competition code");
            System.out.println("2. Load by directory/full file name");
            System.out.println("3. Back");

            int choice = getIntInput("Select an option: ");

            switch (choice) {
                case 1:
                    System.out.print("Enter competition abbreviation (ex. 2026nyro, 2026nytr): ");
                    String teamFile = scanner.nextLine();
                    competition.loadInfoCSV("data/"+teamFile+"_team_list.csv");
                    competition.loadPerformanceCSV("data/"+teamFile+"_coprs.csv");
                    break;
                case 2:
                    System.out.print("Enter team info CSV filename: ");
                    String file1 = scanner.nextLine();

                    System.out.print("Enter performance CSV filename: ");
                    String file2 = scanner.nextLine();

                    competition.loadInfoCSV(file1);
                    competition.loadPerformanceCSV(file2);
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1-3.");
            }
        }
    }

    private static void teamLookupMenu() {
        boolean back = false;

        while (!back) {
            System.out.println();
            System.out.println("---- TEAM LOOKUP ----");
            System.out.println("1. Search by Team Number");
            System.out.println("2. View All Teams");
            System.out.println("3. Back");

            int choice = getIntInput("Select an option: ");

            switch (choice) {
                case 1:
                    int teamNumber = getIntInput("Enter team number: ");
                    FrcTeam team = competition.findTeam(teamNumber);

                    if (team == null) {
                        System.out.println("Team not found.");
                    } else {
                        System.out.println();
                        System.out.println(team.getFullSummary());
                    }
                    break;
                case 2:
                    competition.printAllTeams();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1-3.");
            }
        }
    }

    private static void rankingsMenu() {
        boolean back = false;

        while (!back) {
            System.out.println();
            System.out.println("---- RANKINGS ----");
            System.out.println("1. Top 10 Teams by OPR");
            System.out.println("2. Top 10 Teams by Total Score");
            System.out.println("3. Full Rankings by OPR");
            System.out.println("4. Full Rankings by Total Score");
            System.out.println("5. Back");

            int choice = getIntInput("Select an option: ");

            switch (choice) {
                case 1:
                    competition.printTopTeamsOPR(10);
                    break;
                case 2:
                    competition.printTopTeamsTotScore(10);
                    break;
                case 3:
                    competition.printTopTeamsOPR(9999);
                    break;
                case 4:
                    competition.printTopTeamsTotScore(9999);
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1-5.");
            }
        }
    }

    private static void allianceMenu() {
        boolean back = false;

        while (!back) {
            System.out.println();
            System.out.println("---- ALLIANCE TOOLS ----");
            System.out.println("1. Compare Two Alliances");
            System.out.println("2. Back");

            int choice = getIntInput("Select an option: ");

            switch (choice) {
                case 1:
                    compareAlliances();
                    break;
                case 2:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1-2.");
            }
        }
    }

    private static void compareAlliances() {
        System.out.println();
        System.out.println("Enter 3 team numbers for Alliance 1.");
        Alliance alliance1 = buildAlliance();

        System.out.println();
        System.out.println("Enter 3 team numbers for Alliance 2.");
        Alliance alliance2 = buildAlliance();

        double score1 = alliance1.getAllianceScore();
        double score2 = alliance2.getAllianceScore();

        System.out.println();
        System.out.println("==== ALLIANCE COMPARISON ====");
        System.out.println("Alliance 1:");
        System.out.println(alliance1.getSummary());
        System.out.println("Score: " + score1);

        System.out.println();

        System.out.println("Alliance 2:");
        System.out.println(alliance2.getSummary());
        System.out.println("Score: " + score2);

        System.out.println();

        if (score1 > score2) {
            System.out.println("Predicted Winner: Alliance 1");
        } else if (score2 > score1) {
            System.out.println("Predicted Winner: Alliance 2");
        } else {
            System.out.println("Prediction: Tie");
        }
    }

    private static Alliance buildAlliance() {
        FrcTeam[] selectedTeams = new FrcTeam[3];

        for (int i = 0; i < selectedTeams.length; i++) {
            FrcTeam team = null;

            while (team == null) {
                int teamNumber = getIntInput("Team " + (i + 1) + ": ");
                team = competition.findTeam(teamNumber);

                if (team == null) {
                    System.out.println("Team not found. Try again.");
                }
            }

            selectedTeams[i] = team;
        }

        return new Alliance(selectedTeams);
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);

            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }
}