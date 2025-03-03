package com.watu.scheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CsvLoader {

    public static List<Action> loadCsvFile(String filePath) throws IOException {
        List<Action> actions = new ArrayList<>();
        try (InputStream inputStream = CsvLoader.class.getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip the header line
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    LocalTime time = LocalTime.parse(parts[0]);
                    int bitmask = Integer.parseInt(parts[1]);
                    actions.add(new Action(time, bitmask));
                }
            }
        }
        return actions;
    }
}