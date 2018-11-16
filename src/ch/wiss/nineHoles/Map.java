package ch.wiss.nineHoles;

public class Map {

	
String fieldempty = "( )";
String verticalSeperation = "-";
String horizontalSeperation = "|";
String letters[] = {"a","b","c"};


public void displayMap(){
	for(int i = 0; i < 3; i++) {
		if(i<1) {
		System.out.print("  ");
		}else {
		System.out.print("   ");
		}
		System.out.print(i+1);
	}
	System.out.println("");
	for(int y = 0; y < 3; y++) {
		System.out.print(letters[y]);
		for(int x = 0; x < 3; x++) {
			System.out.print(fieldempty);
			if(x<2) {
				System.out.print(verticalSeperation);
			}
		}
		System.out.println("");
		
		if(y<2) {
		for(int i = 0; i < 3; i++) {
			if(i<1) {
			System.out.print("  ");
			}else {
			System.out.print("   ");
			}
			System.out.print("|");
		}
		System.out.println("");
	}
	}
}

public void displayLogo() {
	System.out.println("   ___      _    _    ____    _        ______    _____ ");
	System.out.println("  / _ \\    | |  | |  / __ \\  | |      |  ____|  / ____|");
	System.out.println(" | (_) |   | |__| | | |  | | | |      | |__    | (___  ");
	System.out.println("  \\__, |   |  __  | | |  | | | |      |  __|    \\___ \\ ");
	System.out.println("    / /    | |  | | | |__| | | |____  | |____   ____) |");
	System.out.println("   /_/     |_|  |_|  \\____/  |______| |______| |_____/ ");
	System.out.println("");
}


}