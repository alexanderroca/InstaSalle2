import java.util.Scanner;

public class SubMenu {
    void mostraSubMenuCami(int opcio){
        String cas;
        Scanner sc = new Scanner(System.in);
        do {
            // Pintem el menú:
            System.out.println("\nCamins:");
            System.out.println("1. Cami més fiable");
            System.out.println("2. Cami amb mínim cost\n");
            System.out.print("Sel·lecciona una opcio: ");
            cas = sc.nextLine();

            switch (cas) {
                case "1":
                    opcio = Integer.valueOf(cas);
                    mostraSubMenuAlgoritme(opcio);
                    break;
                case "2":
                    opcio = Integer.valueOf(cas);
                    mostraSubMenuAlgoritme(opcio);
                    break;
                default:
                    System.out.println("\nError, opció incorrecta");
            }
        }while(!cas.equals("1") && !cas.equals("2"));
    }

    void mostraSubMenuAlgoritme(int opcio){
        String cas;
        Scanner sc = new Scanner(System.in);

        do {
            // Pintem el menú:
            System.out.println("\nVolem utilitzar:");
            System.out.println("1. Backtracking");
            System.out.println("2. Branch & Bound");
            System.out.println("3. Greedy");
            System.out.println("4. Greedy + Backtracking");
            System.out.println("5. Greedy + Branch & Bound\n");
            System.out.print("Sel·lecciona una opcio: ");
            cas = sc.nextLine();

            switch (cas) {
                case "1":
                    System.out.println("\nNo implementat\n");
                    break;
                case "2":
                    System.out.println("\nNo implementat\n");
                    break;
                case "3":
                    System.out.println("\nNo implementat\n");
                    break;
                case "4":
                    System.out.println("\nNo implementat\n");
                    break;
                case "5":
                    System.out.println("\nNo implementat\n");
                    break;
                default:
                    System.out.println("\nError, opció incorrecta");
            }
        }while(!cas.equals("1") && !cas.equals("2") && !cas.equals("3") && !cas.equals("4") && !cas.equals("5"));
    }

}
