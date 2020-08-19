package com.alerting.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class WarningControllerTest {

    private final WarningController wc = new WarningController();

    @Test
    public void getGreeting() {
        Assertions.assertEquals("Greetings!", wc.index());
    }

    @Test
    public void assertSanitizeInput() throws Exception {
        Assertions.assertEquals("(No Alert, 1)", wc.createServerData("(1,2,3,4)"));
        Assertions.assertEquals("(No Alert, 1)", wc.createServerData("(1,   2, 3,4)  "));
        Assertions.assertEquals("(No Alert, 1)", wc.createServerData("   (  1,   2, 3,4"));
    }

    @Test
    public void assertInvalidInput() throws Exception {

        Assertions.assertEquals(wc.createServerData("(1,2,3,"), "Invalid input. Accepted format: #,#,#,#");
        Assertions.assertEquals(wc.createServerData("1,2,3,)"), "Invalid input. Accepted format: #,#,#,#");
    }

    @Test
    public void assertNoAlert() throws Exception {
        Assertions.assertEquals("(No Alert, 1)", wc.createServerData("1,2,3,4"));
        Assertions.assertEquals("(No Alert, 2234)", wc.createServerData("2234,81,62,55"));
        Assertions.assertEquals("(No Alert, 1)", wc.createServerData("(1,2,3,4"));
        Assertions.assertEquals("(No Alert, 2234)", wc.createServerData("(2234,81,62,55)"));
    }

    @Test
    public void assertCPUAlert() throws Exception {
        Assertions.assertEquals("(Alert, 1234, CPU_UTILIZATION VIOLATED)", wc.createServerData("1234,90,50,40"));
        Assertions.assertEquals("(Alert, 1234, CPU_UTILIZATION VIOLATED)", wc.createServerData("(1234,90,50,40)"));
    }

    @Test
    public void assertMemoryAlert() throws Exception {
        Assertions.assertEquals("(Alert, 123, MEMORY_UTILIZATION VIOLATED)", wc.createServerData("123,50,90,40"));
        Assertions.assertEquals("(Alert, 123, MEMORY_UTILIZATION VIOLATED)", wc.createServerData("(123,50,90,40)"));
    }
    @Test
    public void assertDiskAlert() throws Exception {
        Assertions.assertEquals("(Alert, 123, DISK_UTILIZATION VIOLATED)", wc.createServerData("123,50,40,90"));
        Assertions.assertEquals("(Alert, 123, DISK_UTILIZATION VIOLATED)", wc.createServerData("(123,50,40,90)"));
    }

    @Test
    public void assertCPUAndMemoryAlert() throws Exception {
        Assertions.assertEquals("(Alert, 1234, CPU_UTILIZATION VIOLATED, MEMORY_UTILIZATION VIOLATED)", wc.createServerData("1234,89,80,35"));
        Assertions.assertEquals("(Alert, 1234, CPU_UTILIZATION VIOLATED, MEMORY_UTILIZATION VIOLATED)", wc.createServerData("(1234,89,80,35)"));
    }

    @Test
    public void assertCPUAndDiskAlert() throws Exception {
        Assertions.assertEquals("(Alert, 1234, CPU_UTILIZATION VIOLATED, DISK_UTILIZATION VIOLATED)", wc.createServerData("1234,89,69,65"));
        Assertions.assertEquals("(Alert, 1234, CPU_UTILIZATION VIOLATED, DISK_UTILIZATION VIOLATED)", wc.createServerData("(1234,89,69,65)"));
    }

    @Test
    public void assertMemoryAndDiskAlert() throws Exception {
        Assertions.assertEquals("(Alert, 1234, MEMORY_UTILIZATION VIOLATED, DISK_UTILIZATION VIOLATED)", wc.createServerData("1234,70,80,70"));
        Assertions.assertEquals("(Alert, 1234, MEMORY_UTILIZATION VIOLATED, DISK_UTILIZATION VIOLATED)", wc.createServerData("(1234,70,80,70)"));
    }

}
