package tn.esprit.eventsproject.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.eventsproject.services.EventServicesImpl;

import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.repositories.ParticipantRepository;

public class EventServiceImplTest {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private EventServicesImpl eventService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddParticipant() {
        // Create a sample participant
        Participant sampleParticipant = new Participant();
        sampleParticipant.setIdPart(1);
        sampleParticipant.setNom("John");
        sampleParticipant.setPrenom("Doe");
        sampleParticipant.setTache(Tache.INVITE); // Set the Tache enum value

        // Mock the repository behavior
        when(participantRepository.save(any(Participant.class))).thenReturn(sampleParticipant);

        // Call the service method
        Participant result = eventService.addParticipant(sampleParticipant);

        // Verify that the repository save method was called with the correct argument
        verify(participantRepository, times(1)).save(eq(sampleParticipant));

        // Verify that the returned participant is the same as the sample participant
        assertEquals(sampleParticipant, result);
    }
}
