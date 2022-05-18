package com.telegaming.helpi;

import com.telegaming.helpi.domain.model.TrainingMaterial;
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
public class TrainingMaterialServiceImplTest {

    @MockBean
    private TrainingMaterialRepository trainingMaterialRepository;

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
    public void whenGetTrainingMaterialByIdWithValidIdThenReturnTrainingMaterial(){
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
    public void whenCreateTrainingMaterialThenReturnTrainingMaterialCreatedTrainingMaterial(){
        //Arrange
        Long id = 1L;
        TrainingMaterial trainingMaterial = new TrainingMaterial();
        trainingMaterial.setTrainingMaterialId(id);

        when(trainingMaterialRepository.save(trainingMaterial))
                .thenReturn(trainingMaterial);
        //Act
        TrainingMaterial foundTrainingMaterial = trainingMaterialService.createTrainingMaterial(trainingMaterial);
        //Assert
        assertThat(foundTrainingMaterial.getTrainingMaterialId()).isEqualTo(id);
    }

    @Test
    @DisplayName("When updateTrainingMaterial Then Return updated TrainingMaterial")
    public void whenUpdateTrainingMaterialThenReturnUpdatedTrainingMaterial(){
        //Arrange
        Long id = 1L;
        TrainingMaterial trainingMaterial = new TrainingMaterial();
        trainingMaterial.setTrainingMaterialId(id);

        String updateTitle = "updateTitle";
        String updateDescription = "updateDescription";
        TrainingMaterial updateTrainingMaterial = new TrainingMaterial();
        updateTrainingMaterial.setTitle(updateTitle);
        updateTrainingMaterial.setTrainingDescription(updateDescription);

        when(trainingMaterialRepository.save(trainingMaterial))
                .thenReturn(trainingMaterial);
        when(trainingMaterialRepository.findById(id))
                .thenReturn(Optional.of(trainingMaterial));
        //Act
        TrainingMaterial foundTrainingMaterial = trainingMaterialService.createTrainingMaterial(trainingMaterial);
        trainingMaterialService.updateTrainingMaterial(id, updateTrainingMaterial);
        //Assert
        assertThat(foundTrainingMaterial.getTitle()).isEqualTo(updateTitle);
    }

    @Test
    @DisplayName("When getTrainingMaterialById With Invalid Id Then Returns Resource Not Found Exception")
    public void whenGetTrainingMaterialByIdWithInvalidIdThenReturnsResourceNotFoundException(){
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
