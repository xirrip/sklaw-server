package ch.skunky.springdemo.service;

import ch.skunky.springdemo.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    private ClientService clientService;

    @BeforeEach
    public void setUp() throws Exception {
        this.clientService = new ClientServiceImpl(clientRepository);
    }

    @Test
    public void should_find_empty_list(){
        assertTrue(clientService.findAll().isEmpty());
    }

    /*
    @Test
    public void should_except_if_client_not_found(){
        long id=3;
        Mockito.when(clientRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(HttpClientErrorException.class, () -> clientService.getClient(id));
    }
    */

    @Test
    public void should_be_empty_if_client_not_found(){
        long id=3;
        Mockito.when(clientRepository.findById(id)).thenReturn(Optional.empty());
        assertTrue(clientService.getClient(id).isEmpty());
    }

}
