package queuedesk.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import queuedesk.TicketManager.BugTicket;
import queuedesk.TicketManager.Priority;
import queuedesk.TicketManager.Severity;
import queuedesk.TicketManager.Ticket;
import queuedesk.ticketRepository.TicketSink;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

// MockitoExtension creates objects declared with @Mock.
@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    // Mocking an interface avoids Byte Buddy trying to instrument the concrete Repository class.
    @Mock
    private TicketSink<Ticket> ticketSink;

    private TicketService ticketService;

    @TempDir
    private Path tempDirectory;

    @BeforeEach
    void setUp() {
        // A fresh service for every test prevents tests from sharing mutable ticket state.
        ticketService = new TicketService();
    }

    @Test
    void loadFile_shouldLoadTicketsFromARealFile() throws Exception {
        // Arrange: create a temporary file in the format expected by TicketFileLoader.
        Path ticketFile = tempDirectory.resolve("tickets.txt");
        Files.writeString(ticketFile,
                "BUG|Login failure|Unable to sign in|HIGH|Asha|HIGH|Open login page\n");

        // Act and Assert: real file loading verifies service and builder integration.
        assertDoesNotThrow(() -> ticketService.loadFile(ticketFile.toString()));
    }

    @Test
    void createRepository_shouldAddEveryLoadedTicket() throws Exception {
        // Arrange: load a real two-line file.
        Path ticketFile = tempDirectory.resolve("tickets.txt");
        Files.writeString(ticketFile,
                "BUG|First|First description|HIGH|Asha|LOW|Step one\n" +
                "BUG|Second|Second description|MEDIUM|Ravi|MEDIUM|Step two\n");
        ticketService.loadFile(ticketFile.toString());

        // Act: TicketService adds each loaded ticket to the mock boundary.
        ticketService.createRepository(ticketSink);

        // Assert: Mockito verifies that both tickets reached the repository boundary.
        verify(ticketSink, times(2)).add(any(Ticket.class));
    }

    @Test
    void createRepository_shouldDoNothingWhenNoTicketsWereLoaded() {
        // Calling createRepository before loadFile represents the empty initial state.
        ticketService.createRepository(ticketSink);

        verifyNoInteractions(ticketSink);
    }

    @Test
    void bugTicketBuilder_shouldSetBuilderValuesAndDefaults() {
        // Arrange and Act: Lombok generates this builder implementation.
        BugTicket ticket = BugTicket.builder()
                .title("NullPointerException")
                .description("App crashes")
                .requester("Ravi")
                .priority(Priority.HIGH)
                .severity(Severity.HIGH)
                .stepsToReproduce("Open checkout")
                .build();

        // Assert: supplied values and Lombok/@Builder.Default values are present.
        assertEquals("NullPointerException", ticket.getTitle());
        assertEquals(Priority.HIGH, ticket.getPriority());
        assertEquals(Severity.HIGH, ticket.getSeverity());
        assertEquals("Open checkout", ticket.getStepsToReproduce());
        assertEquals("Ravi", ticket.getRequester());
    }
}
