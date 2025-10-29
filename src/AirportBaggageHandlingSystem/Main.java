package AirportBaggageHandlingSystem;

import java.time.LocalDate;

import AirportBaggageHandlingSystem.FileManager;
import AirportBaggageHandlingSystem.LogRecord;
import AirportBaggageHandlingSystem.LogService;
import AirportBaggageHandlingSystem.RegexSearch;
import AirportBaggageHandlingSystem.TaskSimulator;

public class Main {

    public static void main(String[] args) {
        System.out.println("Airport Luggage System:");

        // Simulate device message (only once at startup)
        TaskSimulator.simulateDataExchange("VEH123", "Vehicle started charging");
        TaskSimulator.simulateDataExchange("VEH124", "Vehical stzarted charging");

        while (true) {
            // Create logs
            LogService.writeLog("vehicles", new LogRecord("VEH123", "Charging started"));
            LogService.writeLog("vehicles", new LogRecord("VEH124", "Charging started"));
            LogService.writeLog("chargers", new LogRecord("CHG01", "Charger ready"));
            LogService.writeLog("chargers", new LogRecord("CHG02", "Charger ready"));
            LogService.writeLog("system",   new LogRecord("SYS", "System OK"));

            System.out.println("\n--- Vehicle Logs ---");
            LogService.readLog("vehicles", LocalDate.now().toString());

            System.out.println("\n--- Regex Search for VEH123 ,So On---");
            RegexSearch.search("vehicles", "VEH123");
            RegexSearch.search("vehicles", "VEH124");

            
            FileManager.archiveLogs("data/logs", "daily_logs");

      
            try {
                System.out.println("Auto Updated data loading: ");
                Thread.sleep(5 * 60 * 1000);
            } catch (InterruptedException e) {
                System.out.println("Auto reload interrupted: " + e.getMessage());
                break; 
            }
        }
    }
}
