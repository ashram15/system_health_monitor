package org.example;

// Import all the tools we need
import com.fasterxml.jackson.databind.ObjectMapper;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    // --- Configuration ---
    // This is the "Spy Headquarters" address we're reporting to.
    private static final String SERVER_URL = "http://localhost:8080/api/v1/metrics";

    // We'll use these to make our API calls
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // We'll use Oshi to get system info
    private static final SystemInfo si = new SystemInfo();
    private static final HardwareAbstractionLayer hal = si.getHardware();
    private static final OperatingSystem os = si.getOperatingSystem();
    // --- End Configuration ---


    /**
     * The main method. This is the entry point of our Agent application.
     * It just sets up a scheduler to run our metric collection task every minute.
     */
    public static void main(String[] args) {
        System.out.println("--- System Monitor Agent Started ---");
        System.out.println("Will send metrics to: " + SERVER_URL);

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // This is the task that will run. It's a "lambda" (a simple, anonymous function).
        Runnable collectionTask = () -> {
            try {
                // This is the core logic: collect, then send.
                System.out.println("Collecting metrics...");
                SystemMetric metric = collectMetrics();

                System.out.println("Sending metrics...");
                sendMetrics(metric);

                System.out.println("...Metrics sent successfully.");
            } catch (Exception e) {
                // If anything fails (e.g., server is down), just print the error and continue.
                System.err.println("Failed to collect or send metrics: " + e.getMessage());
            }
        };

        // Schedule the task to run every 1 minute, starting immediately (0 delay).
        scheduler.scheduleAtFixedRate(collectionTask, 0, 1, TimeUnit.MINUTES);
    }

    /**
     * This method uses Oshi to collect the real system health metrics.
     * @return A SystemMetric object filled with data.
     * @throws InterruptedException if the CPU-measuring sleep is interrupted.
     */
    private static SystemMetric collectMetrics() throws InterruptedException {
        CentralProcessor processor = hal.getProcessor();

        // --- Get CPU Usage ---
        // This is the tricky one. We must measure over a period of time.
        // We get the "ticks" (usage counters) *before* the sleep.
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        TimeUnit.SECONDS.sleep(1);
        // We get the CPU load by comparing the *previous* ticks to the *current* ticks.
        // Oshi handles getting the new ticks internally.
        double cpuLoad = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;

        // --- Get RAM Usage ---
        GlobalMemory memory = hal.getMemory();
        double ramUsage = (double)(memory.getTotal() - memory.getAvailable()) / memory.getTotal(); // Get ratio

        // --- Get Disk Usage ---
        // We'll just find the biggest hard drive and use that.
        List<OSFileStore> fileStores = os.getFileSystem().getFileStores();
        OSFileStore mainStore = fileStores.get(0);
        double diskUsage = (double)(mainStore.getTotalSpace() - mainStore.getUsableSpace()) / mainStore.getTotalSpace(); // Get ratio

        // --- Get Hostname ---
        String hostname = os.getNetworkParams().getHostName();

        // --- Package it all up ---
        SystemMetric metric = new SystemMetric();
        metric.setHostname(hostname);
        metric.setCpuUsage(cpuLoad);
        metric.setRamUsage(ramUsage * 100); // Send as percentage
        metric.setDiskUsage(diskUsage * 100); // Send as percentage

        return metric;
    }

    /**
     * This method takes our metric object, converts it to JSON, and sends it to our server.
     * @param metric The SystemMetric object to send.
     * @throws Exception if the network request fails or JSON conversion fails.
     */
    private static void sendMetrics(SystemMetric metric) throws Exception {
        // 1. Convert our Java object into a JSON string
        String jsonBody = objectMapper.writeValueAsString(metric);

        // 2. Build the HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SERVER_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        // 3. Send the request and get the response
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // We can check the response code to make sure it worked (e.g., 200 OK)
        if (response.statusCode() != 200) {
            System.err.println("Error: Server responded with status code " + response.statusCode());
        }
    }
}