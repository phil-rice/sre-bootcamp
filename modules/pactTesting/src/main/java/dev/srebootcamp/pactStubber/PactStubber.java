package dev.srebootcamp.pactStubber;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface PactStubber {

    static public Process startPacts(String directory, String provider, int port) throws IOException {
        shutdownPacts(port);
        File folder = new File(directory);
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith(provider + "-") && name.endsWith(".json");
            }
        };
        String[] files = folder.list(filter);
        if (files == null || files.length == 0)
            throw new RuntimeException("No pacts found for provider " + provider + " in directory " + directory);
        String[] filesWithPaths = Arrays.stream(files).map(f -> directory + "/" + f).toArray(String[]::new);
        var names = String.join(",", filesWithPaths);
        String cmd = "cmd /c pact-stub-service -p " + port + " " + names + "\n";
        System.out.println("Starting pacts with command: " + cmd);
        Process process = Runtime.getRuntime().exec(cmd);
        var in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while (true) {
            var line = in.readLine();
            System.out.println(line);
            if (line.contains("WEBrick::HTTPServer#start")) {
                new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                String line = in.readLine();
                                if (line == null) return;
                                System.out.println(line);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
                return process;
            }
        }
    }

    static class ListenerThread extends Thread {
        private final InputStream stream;
        private final StringBuffer builder;
        private boolean log;
        private boolean exit = false;

        public void exit() {
            exit = true;
        }

        public ListenerThread(InputStream stream, StringBuffer builder, boolean log) {
            this.stream = stream;
            this.builder = builder;
            this.log = log;
        }

        @Override
        public void run() {
            var in = new BufferedReader(new InputStreamReader(stream));
            while (!exit) {
                try {
                    String line = in.readLine();
                    if (line != null) {
                        if (log) System.out.println(" .. " + line);
                        builder.append(line).append("\n");
                    } else Thread.sleep(100);

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static ListenerThread listenTo(InputStream stream, StringBuffer builder, boolean log) {
        ListenerThread result = new ListenerThread(stream, builder, log);
        result.start();
        return result;
    }

    public static String callAndGetOutput(String cmd, boolean log) {
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            var builder = new StringBuffer();
            listenTo(process.getInputStream(), builder, log);
            listenTo(process.getErrorStream(), builder, log);
            process.waitFor(5, TimeUnit.SECONDS);
            if (!process.isAlive()) return builder.toString();
            process.destroy();
            throw new RuntimeException("Process timed out: " + cmd + " output so far: " + builder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Set<String> pidsListeningOnPort(int port) {
        String cmd = "netstat -ano";
        System.out.println("Pids cmd: " + cmd);
        String raw = callAndGetOutput(cmd, false);
        List<String> lines = Arrays.asList(raw.split("\n"));
        Set<String> forThisPort = lines.stream().filter(line -> line.contains(":" + port)).collect(Collectors.toSet());

        Pattern pattern = Pattern.compile("\\s([0-9]+)$");
        Set<String> result = new HashSet<>();
        for (String line : forThisPort) {
            var matcher = pattern.matcher(line);
            if (matcher.find()) {
                result.add(matcher.group(1));
            }
        }
        return result;
    }


    static public void shutdownPacts(int port) {
        var pids = pidsListeningOnPort(port);
        var current = ProcessHandle.current().pid();
        System.out.println("Killing pacts on port: " + port + " Pids: " + pids + " current: " + current);
        pids.remove(Long.toString(current));
        for (var pid : pids)
            System.out.println("pid: " + pid + "  " + callAndGetOutput("taskkill /F /PID " + pid, false));
    }
}
