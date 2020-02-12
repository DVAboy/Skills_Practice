package sample;

import java.io.*;
import java.util.ArrayList;

public class FileReader {
    private String fileName;
    private ArrayList<String[]> stats = new ArrayList<>();

    public ArrayList<String> readFile() {
        String line;
        String[] temp;

        ArrayList<String> vars = new ArrayList<>();

        try {
            java.io.FileReader fileReader = new java.io.FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //skips a line
            line = bufferedReader.readLine();

            //reads and interprets file
            while ((line = bufferedReader.readLine()) != null) {
                temp = line.split(",");
                stats.add(temp);
                vars.add(temp[0]);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return vars;
    }

    public void addWeapon(ArrayList<String[]> stats) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            fileWriter.flush();

            int i = 0;
            String[] temp;

            //writes each arrayList to StoredData.txt
            bufferedWriter.write("Name,Damage,type,Speed");

            while (stats.get(i) != null) {
                temp = stats.get(i);
                for (int l = 0; l < 4; l++) {
                    bufferedWriter.write(temp[l]);
                }
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
    }

    ////////////////////Constructor////////////////////
    FileReader(String fileName) {
        this.fileName = fileName;
    }

    ////////////////////Getters and Setters////////////////////
    public ArrayList<String[]> getStats() {
        return stats;
    }
}
