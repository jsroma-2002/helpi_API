package com.telegaming.helpi;

import com.telegaming.helpi.domain.model.Coach;
import com.telegaming.helpi.domain.repository.CoachRepository;
import com.telegaming.helpi.domain.repository.GameRepository;
import com.telegaming.helpi.domain.service.CoachService;
import com.telegaming.helpi.exception.ResourceNotFoundException;
import com.telegaming.helpi.service.CoachServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CoachServiceImplTest {

    @MockBean
    private CoachRepository coachRepository;

    @MockBean
    private GameRepository gameRepository;

    @Autowired
    private CoachService coachService;

    @TestConfiguration
    static class CoachServiceImplTestConfiguration{
        @Bean
        public CoachService coachService(){return new CoachServiceImpl();}
    }

    @Test
    @DisplayName("When getCoach by Id with valid Id then return Coach")
    public void whenGetCoachByIdWithValidIdThenReturnCoach(){
        //Arrange
        Long id = 1L;
        Coach coach = new Coach();
        coach.setId(id);
        when(coachRepository.findById(id))
                .thenReturn(Optional.of(coach));
        //Act
        Coach foundCoach = coachService.getCoachById(id);
        //Assert
        assertThat(foundCoach.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("When createCoach Then Return created Coach")
    public void whenCreateCoachThenReturnCoachCreatedCoach(){
        //Arrange
        Long id = 1L;
        Coach coach = new Coach();
        coach.setId(id);

        when(coachRepository.save(coach))
                .thenReturn(coach);
        //Act
        Coach foundCoach = coachService.createCoach(coach);
        //Assert
        assertThat(foundCoach.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("When updateCoach then return update Coach")
    public void whenUpdateCoachThenReturnUpdateCoach(){
        //Arrange
        Long id = 1L;
        Coach coach = new Coach();
        coach.setId(id);

        String updateEmail = "updateEmail";
        String updatePassword = "updatePass";
        Coach updateCoach = new Coach();
        updateCoach.setEmail(updateEmail);
        updateCoach.setPassword(updatePassword);

        when(coachRepository.save(coach))
                .thenReturn(coach);
        when(coachRepository.findById(id))
                .thenReturn(Optional.of(coach));
        //Act
        Coach foundCoach = coachService.createCoach(coach);
        coachService.updateCoach(id,updateCoach);
        //Assert
        assertThat(foundCoach.getEmail()).isEqualTo(updateEmail);
    }

    @Test
    @DisplayName("When getCoachById With Invalid Id Then Returns Resource Not Found Exception")
    public void whenGetCoachByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(coachRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template,"Coach", "Id", id);
        //Act
        Throwable exception = catchThrowable(() -> {
            Coach foundCoach = coachService.getCoachById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
