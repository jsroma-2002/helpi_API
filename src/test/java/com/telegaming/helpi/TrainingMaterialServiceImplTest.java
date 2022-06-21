package com.telegaming.helpi;

import com.telegaming.helpi.domain.model.Game;
import com.telegaming.helpi.domain.model.TrainingMaterial;
import com.telegaming.helpi.domain.repository.GameRepository;
import com.telegaming.helpi.domain.repository.PlayerRepository;
import com.telegaming.helpi.domain.repository.TrainingMaterialRepository;
import com.telegaming.helpi.domain.service.TrainingMaterialService;
import com.telegaming.helpi.exception.ResourceNotFoundException;
import com.telegaming.helpi.service.TrainingMaterialServiceImpl;
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
class TrainingMaterialServiceImplTest {

    @MockBean
    private TrainingMaterialRepository trainingMaterialRepository;

    @MockBean
    private GameRepository gameRepository;

    @MockBean
    private PlayerRepository playerRepository;

    @Autowired
    private TrainingMaterialService trainingMaterialService;

    @TestConfiguration
    static class TrainingMaterialServiceImplTestConfiguration{
        @Bean
        public TrainingMaterialService trainingMaterialService(){
            return new TrainingMaterialServiceImpl();
        }
    }

    @Test
    @DisplayName("When getTrainingMaterialById With Valid Id Then Return TrainingMaterial")
    void whenGetTrainingMaterialByIdWithValidIdThenReturnTrainingMaterial(){
        //Arrange
        Long id = 1L;
        TrainingMaterial trainingMaterial = new TrainingMaterial();
        trainingMaterial.setTrainingMaterialId(id);
        when(trainingMaterialRepository.findById(id))
                .thenReturn(Optional.of(trainingMaterial));
        //Act
        TrainingMaterial foundTrainingMaterial = trainingMaterialService.getTrainingMaterialById(id);
        //Assert
        assertThat(foundTrainingMaterial.getTrainingMaterialId()).isEqualTo(id);
    }

    @Test
    @DisplayName("When createTrainingMaterial Then Return created TrainingMaterial")
    void whenCreateTrainingMaterialThenReturnTrainingMaterialCreatedTrainingMaterial(){
        //Arrange
        Long id = 1L;
        TrainingMaterial trainingMaterial = new TrainingMaterial();
        trainingMaterial.setTrainingMaterialId(id);

        Long gameId = 1L;
        Game game = new Game();
        game.setGameId(gameId);

        when(trainingMaterialRepository.save(trainingMaterial))
                .thenReturn(trainingMaterial);

        when(gameRepository.findById(gameId))
                .thenReturn(Optional.of(game));

        //Act
        TrainingMaterial foundTrainingMaterial = trainingMaterialService.createTrainingMaterial(trainingMaterial, gameId);
        //Assert
        assertThat(foundTrainingMaterial.getTrainingMaterialId()).isEqualTo(id);
    }

    @Test
    @DisplayName("When updateTrainingMaterial Then Return updated TrainingMaterial")
    void whenUpdateTrainingMaterialThenReturnUpdatedTrainingMaterial(){
        //Arrange
        Long id = 1L;
        TrainingMaterial trainingMaterial = new TrainingMaterial();
        trainingMaterial.setTrainingMaterialId(id);

        Long gameId = 1L;
        Game game = new Game();
        game.setGameId(gameId);

        String updateTitle = "updateTitle";
        String updateDescription = "updateDescription";
        TrainingMaterial updateTrainingMaterial = new TrainingMaterial();
        updateTrainingMaterial.setTitle(updateTitle);
        updateTrainingMaterial.setTrainingDescription(updateDescription);

        when(trainingMaterialRepository.save(trainingMaterial))
                .thenReturn(trainingMaterial);
        when(trainingMaterialRepository.findById(id))
                .thenReturn(Optional.of(trainingMaterial));
        when(gameRepository.findById(gameId))
                .thenReturn(Optional.of(game));
        //Act
        TrainingMaterial foundTrainingMaterial = trainingMaterialService.createTrainingMaterial(trainingMaterial, gameId);
        trainingMaterialService.updateTrainingMaterial(id, updateTrainingMaterial);
        //Assert
        assertThat(foundTrainingMaterial.getTitle()).isEqualTo(updateTitle);
    }

    @Test
    @DisplayName("When getTrainingMaterialById With Invalid Id Then Returns Resource Not Found Exception")
    void whenGetTrainingMaterialByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(trainingMaterialRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template,"TrainingMaterial", "Id", id);
        //Act
        Throwable exception = catchThrowable(() -> {
            TrainingMaterial foundTrainingMaterial = trainingMaterialService.getTrainingMaterialById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
