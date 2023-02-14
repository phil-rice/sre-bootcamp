package dev.srebootcamp;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReportIfPactFilesHaveChangedIT {

    File acceptedPactsDirectory = new File("../../pacts/accepted");
    File newPactsDirectory = new File("../../pacts/new");

    @Test
    public void reportIfPactFilesHaveChanged() throws IOException {
        if (!newPactsDirectory.isDirectory())
            fail("No new pacts directory found: Have you run the unit tests?");
        if (!acceptedPactsDirectory.isDirectory())
            fail("No accept pacts directory found. This shouldn't happen.");
        List<String> fromNewPacts = Arrays.asList(newPactsDirectory.list());
        List<String> fromAcceptedPacts = Arrays.asList(acceptedPactsDirectory.list());
        assertEquals(fromAcceptedPacts, fromNewPacts);
        for (String pactFile : fromAcceptedPacts) {
            String fromNew = Files.readString(new File(newPactsDirectory, pactFile).toPath());
            String fromAccepted = Files.readString(new File(acceptedPactsDirectory, pactFile).toPath());
            assertEquals( fromAccepted, fromNew, "Comparing pact " + pactFile);
        }


    }


}
