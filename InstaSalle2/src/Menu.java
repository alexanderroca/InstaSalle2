import java.util.Scanner;

public class Menu {
    public void mostraMenu(){

        SubMenu submenu = new SubMenu();

        String cas;
        Scanner sc = new Scanner(System.in);

        int opcio = 0;
        // Pintem el menú
        do{
            System.out.println("Menu:");
            System.out.println("1. Trobar servidor");
            System.out.println("2. Distribuir usuaris");
            System.out.println("3. Sortir\n");
            System.out.print("Sel·lecciona una opcio: ");
            cas = sc.nextLine();

            switch (cas) {
                case "1":
                    opcio = Integer.valueOf(cas);
                    break;
                case "2":
                    opcio = Integer.valueOf(cas);
                    submenu.mostraSubMenuAlgoritme(opcio);
                    break;
                case "3":

                    System.out.println("\nGracies per utilitzar el nostre programa !\n");
                    break;
                default:
                    opcio = 0;
                    System.out.println("\nError, opció incorrecta\n");
            }
            if(opcio == 1){
                submenu.mostraSubMenuCami(opcio);
            }
        }while(!cas.equals("3"));
    }
}
