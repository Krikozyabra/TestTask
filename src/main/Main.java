package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import GameLogic.GameLogic;

public class Main {
	public static void main(String[] args) {
		String sPath = System.getProperty("user.dir");
		File file = new File(sPath+"/version.txt");
		if(file.exists()) {
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(file));
				System.out.println(reader.readLine());
			     reader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		GameLogic gameLogic = new GameLogic();
		gameLogic.gameStart();
		
	}
}
