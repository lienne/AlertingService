package com.alerting.service;

import org.springframework.web.bind.annotation.*;

@RestController
public class WarningController {

    private static int SERVER_ID;
    private static int CPU_UTILIZATION;
    private static int MEMORY_UTILIZATION;
    private static int DISK_UTILIZATION;
    private static boolean alert;

    private static StringBuilder alertMessage;
    private static StringBuilder noAlertMessage;

    @RequestMapping("/")
    public String index() {
        return "Greetings!";
    }

    @PostMapping(value="/checkServerUtilization")
    public String createServerData(@RequestBody String message) throws Exception {
        try {
            parseInput(message);

            if(alert) {
                return alertMessage.toString();
            }
            else {
                return  noAlertMessage.toString();
            }
        }
        catch(Exception e) {
            return "Invalid input. Accepted format: #,#,#,#";
        }
    }

    // Eg. (1234,89,69,65) or . (2234,81,62,55)

    private static void parseInput(String input){
        alert = false;
        input = input.trim().replace("(", "").replace(")", "");
        String [] data = input.split(",");

        for(int i = 0; i < data.length; i++) {
            data[i] = data[i].trim();
        }

        SERVER_ID = Integer.parseInt(data[0]);
        CPU_UTILIZATION = Integer.parseInt(data[1]);
        MEMORY_UTILIZATION = Integer.parseInt(data[2]);
        DISK_UTILIZATION = Integer.parseInt(data[3]);

        alertMessage = new StringBuilder("(Alert, " + SERVER_ID);
        noAlertMessage = new StringBuilder("(No Alert, " + SERVER_ID + ")");

        validateInput(CPU_UTILIZATION, MEMORY_UTILIZATION, DISK_UTILIZATION);

        alertMessage.append(")");
    }

    private static void validateInput(int cpu, int memory, int disk) {
        cpuCheck(cpu);
        memoryCheck(memory);
        diskCheck(disk);
    }

    private static void cpuCheck(int cpu_util) {
        if(cpu_util > 85) {
            alert = true;
            alertMessage.append(", CPU_UTILIZATION VIOLATED");
        }
    }

    private static void memoryCheck(int memory_util) {
        if(memory_util > 75) {
            alert = true;
            alertMessage.append(", MEMORY_UTILIZATION VIOLATED");
        }
    }

    private static void diskCheck(int disk_util) {
        if(disk_util > 60) {
            alert = true;
            alertMessage.append(", DISK_UTILIZATION VIOLATED");
        }
    }

}
