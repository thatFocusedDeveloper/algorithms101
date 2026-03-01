package myPractice.round1.datastructures;

import java.io.*;
import java.util.*;

public class Distinct {
    
    // Mode enumeration
    public enum Mode {
        SPLIT_BY_COMMA,  // Extract unique values by splitting each row by comma
        UNIQUE_ROWS,     // Treat each row as a single permission string
        FILTER_INTUIT_SB // Filter rows to keep only Intuit.sb.* prefixed values
    }
    
    public static void main(String[] args) {
        String inputFilePath = System.getProperty("user.home") + "/Desktop/tempIKS_permissions_01.csv";
        
        // Default mode is UNIQUE_ROWS (as per user's change)
        Mode mode = Mode.SPLIT_BY_COMMA;
        
        // Check command line arguments for mode selection
        // if (args.length > 0) {
        //     if (args[0].equalsIgnoreCase("row") || args[0].equalsIgnoreCase("rows")) {
        //         mode = Mode.UNIQUE_ROWS;
        //     } else if (args[0].equalsIgnoreCase("split") || args[0].equalsIgnoreCase("comma")) {
        //         mode = Mode.SPLIT_BY_COMMA;
        //     } else if (args[0].equalsIgnoreCase("filter") || args[0].equalsIgnoreCase("intuit.sb")) {
        //         mode = Mode.FILTER_INTUIT_SB;
        //     } else {
        //         printUsage();
        //         return;
        //     }
        // }
        
        String outputFilePath;
        
        try {
            switch (mode) {
                case SPLIT_BY_COMMA:
                    inputFilePath=System.getProperty("user.home") + "/Desktop/tempIKS_permissions_intuit_sb_filtered.csv";
                    System.out.println("=== Mode: SPLIT BY COMMA ===");
                    System.out.println("Extracting unique values by splitting each row by comma\n");
                    outputFilePath = System.getProperty("user.home") + "/Desktop/tempIKS_permissions_unique_split_unique_rows.csv";
                    Set<String> uniqueStrings = extractUniqueStrings(inputFilePath);
                    writeToCSV(uniqueStrings, outputFilePath);
                    System.out.println("\nProcessing complete!");
                    System.out.println("Total unique strings found: " + uniqueStrings.size());
                    System.out.println("Output file created at: " + outputFilePath);
                    break;
                    
                case UNIQUE_ROWS:
                    System.out.println("=== Mode: UNIQUE ROWS ===");
                    System.out.println("Treating each row as a single permission string\n");
                    outputFilePath = System.getProperty("user.home") + "/Desktop/tempIKS_permissions_unique_rows.csv";
                    Set<String> uniqueRows = extractUniqueRows(inputFilePath);
                    writeToCSV(uniqueRows, outputFilePath);
                    System.out.println("\nProcessing complete!");
                    System.out.println("Total unique strings found: " + uniqueRows.size());
                    System.out.println("Output file created at: " + outputFilePath);
                    break;
                    
                case FILTER_INTUIT_SB:
                    System.out.println("=== Mode: FILTER INTUIT.SB PERMISSIONS ===");
                    System.out.println("Filtering rows to keep only Intuit.sb.* prefixed values\n");
                    String filterInputPath = System.getProperty("user.home") + "/Desktop/tempIKS_permissions_unique_rows.csv";
                    outputFilePath = System.getProperty("user.home") + "/Desktop/tempIKS_permissions_intuit_sb_filtered.csv";
                    int filteredRowCount = filterIntuitSbPermissions(filterInputPath, outputFilePath);
                    System.out.println("\nProcessing complete!");
                    System.out.println("Total rows processed and written: " + filteredRowCount);
                    System.out.println("Output file created at: " + outputFilePath);
                    break;
                    
                default:
                    throw new IllegalStateException("Unknown mode: " + mode);
            }
            
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Prints usage information
     */
    private static void printUsage() {
        System.out.println("Usage: java Distinct [mode]");
        System.out.println();
        System.out.println("Modes:");
        System.out.println("  split, comma   - Extract unique values by splitting rows by comma");
        System.out.println("  row, rows      - Treat each row as a single unique permission string (default)");
        System.out.println("  filter, intuit.sb - Filter rows to keep only Intuit.sb.* prefixed values");
        System.out.println();
        System.out.println("Examples:");
        System.out.println("  java Distinct              # Uses default mode (unique rows)");
        System.out.println("  java Distinct split        # Split by comma mode");
        System.out.println("  java Distinct row          # Unique rows mode");
        System.out.println("  java Distinct filter       # Filter Intuit.sb.* permissions");
    }
    
    /**
     * Extracts all unique strings from the CSV file by splitting each row by comma
     * @param filePath Path to the input CSV file
     * @return Set of unique strings
     * @throws IOException if file reading fails
     */
    private static Set<String> extractUniqueStrings(String filePath) throws IOException {
        Set<String> uniqueStrings = new LinkedHashSet<>();
        
        System.out.println("Reading file: " + filePath);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;
            
            while ((line = reader.readLine()) != null) {
                lineCount++;
                
                // Split by comma and add each string to the set
                String[] values = line.split(",");
                for (String value : values) {
                    String trimmedValue = value.trim();
                    if (!trimmedValue.isEmpty()) {
                        uniqueStrings.add(trimmedValue);
                    }
                }
                
                // Progress indicator for large files
                if (lineCount % 10000 == 0) {
                    System.out.println("Processed " + lineCount + " lines...");
                }
            }
            
            System.out.println("Total lines processed: " + lineCount);
        }
        
        return uniqueStrings;
    }
    
    /**
     * Extracts unique rows treating each entire row as a single permission string
     * @param filePath Path to the input CSV file
     * @return Set of unique row strings
     * @throws IOException if file reading fails
     */
    private static Set<String> extractUniqueRows(String filePath) throws IOException {
        Set<String> uniqueRows = new LinkedHashSet<>();
        
        System.out.println("Reading file: " + filePath);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;
            int duplicateCount = 0;
            
            while ((line = reader.readLine()) != null) {
                lineCount++;
                
                // Treat the entire row as a single permission string
                String trimmedLine = line.trim();
                if (!trimmedLine.isEmpty()) {
                    boolean added = uniqueRows.add(trimmedLine);
                    if (!added) {
                        duplicateCount++;
                    }
                }
                
                // Progress indicator for large files
                if (lineCount % 10000 == 0) {
                    System.out.println("Processed " + lineCount + " lines... (found " + uniqueRows.size() + " unique rows)");
                }
            }
            
            System.out.println("Total lines processed: " + lineCount);
            System.out.println("Duplicate rows found: " + duplicateCount);
        }
        
        return uniqueRows;
    }
    
    /**
     * Filters rows to keep only permissions with "Intuit.sb." prefix
     * @param inputFilePath Path to the input CSV file (unique rows)
     * @param outputFilePath Path to the output CSV file
     * @return Number of rows processed
     * @throws IOException if file reading/writing fails
     */
    private static int filterIntuitSbPermissions(String inputFilePath, String outputFilePath) throws IOException {
        System.out.println("Reading file: " + inputFilePath);
        
        int rowsProcessed = 0;
        int rowsWithIntuitSb = 0;
        int totalIntuitSbPermissions = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            
            String line;
            
            while ((line = reader.readLine()) != null) {
                rowsProcessed++;
                
                // Split the row by comma
                String[] permissions = line.split(",");
                List<String> filteredPermissions = new ArrayList<>();
                
                // Keep only permissions that start with "Intuit.sb."
                for (String permission : permissions) {
                    String trimmed = permission.trim();
                    if (trimmed.startsWith("Intuit.sb.")) {
                        filteredPermissions.add(trimmed);
                    }
                }
                
                // If there are any Intuit.sb.* permissions, write them as a new row
                if (!filteredPermissions.isEmpty()) {
                    rowsWithIntuitSb++;
                    totalIntuitSbPermissions += filteredPermissions.size();
                    
                    // Write the filtered permissions as a comma-separated row
                    writer.write(String.join(",", filteredPermissions));
                    writer.newLine();
                }
                
                // Progress indicator for large files
                if (rowsProcessed % 1000 == 0) {
                    System.out.println("Processed " + rowsProcessed + " rows... (found " + 
                                     rowsWithIntuitSb + " rows with Intuit.sb.* permissions)");
                }
            }
            
            System.out.println("\nTotal rows read: " + rowsProcessed);
            System.out.println("Rows containing Intuit.sb.* permissions: " + rowsWithIntuitSb);
            System.out.println("Total Intuit.sb.* permissions found: " + totalIntuitSbPermissions);
            System.out.println("Output file written successfully!");
        }
        
        return rowsWithIntuitSb;
    }
    
    /**
     * Writes unique strings to output CSV file (one per line)
     * @param uniqueStrings Set of unique strings
     * @param outputPath Path to the output CSV file
     * @throws IOException if file writing fails
     */
    private static void writeToCSV(Set<String> uniqueStrings, String outputPath) throws IOException {
        System.out.println("Writing unique strings to: " + outputPath);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (String str : uniqueStrings) {
                writer.write(str);
                writer.newLine();
            }
        }
        
        System.out.println("Output file written successfully!");
    }
}
