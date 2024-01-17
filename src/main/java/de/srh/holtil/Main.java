package de.srh.holtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String csvPath = "";
        //TODO: Ausgabe zur Aufforderung der Pfadeingabe
        System.out.println("Bitte gib den Pfad zu der Datei ein, " +
                "die in der Datenbank hinzugefügt werden soll");

        //TODO: Scanner der die Pfadeingabe aufnimmt
        Scanner scanner = new Scanner(System.in);
        csvPath = scanner.nextLine();

        System.out.println(csvPath);

        //TODO: Daten aus Datei auslesen und als Text ausgeben

        List<List<String>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                //TODO: Hochkomma herausnehmen (evtl. gelöst)
                records.add(Arrays.asList(values));
                System.out.println(Arrays.asList(values));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println(records.get(0).get(0));
        //TODO: Daten via insert in SQL tranferieren

        System.out.println(records.size());
        MySQLConnection connection = new MySQLConnection(null);

        String insertSql = "insert into cities (land, city, function )" + "values(%s, %s, %s)";
        for(List<String> row : records){
            String readySql = String.format(insertSql, row.get(0), row.get(1), row.get(2));

            System.out.println(readySql);
            connection.addCity(row.get(0), row.get(1), row.get(2) );

        }

        //TODO: DB-Verbindung aufbauen
/*
        MySQLConnection connection = new MySQLConnection(null);

        connection.getConnection();
*/
        //TODO: Daten in DB speichern


        //TODO: DB auf dopplungen prüfen

        //TODO: Tabelle löschen & neu anlegen

        //TODO: Fehler abfangen

    }
}