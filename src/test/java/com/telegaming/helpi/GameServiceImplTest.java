package com.telegaming.helpi;

import com.telegaming.helpi.domain.model.Game;
import com.telegaming.helpi.domain.repository.GameRepository;
import com.telegaming.helpi.domain.service.GameService;
import com.telegaming.helpi.exception.ResourceNotFoundException;
import com.telegaming.helpi.service.GameServiceImpl;
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
class GameServiceImplTest {

    @MockBean
    private GameRepository gameRepository;

    @Autowired
    private GameService gameService;

    @TestConfiguration
    static class GameServiceImplTestConfiguration{
        @Bean
        public GameService gameService(){
            return new GameServiceImpl();
        }
    }

    @Test
    @DisplayName("When getGameById With Valid Id Then Return Game")
    void whenGetGameByIdWithValidIdThenReturnGame(){
        //Arrange
        Long id = 1L;
        Game game = new Game();
        game.setGameId(id);
        when(gameRepository.findById(id))
                .thenReturn(Optional.of(game));
        //Act
        Game foundGame = gameService.getGameById(id);
        //Assert
        assertThat(foundGame.getGameId()).isEqualTo(id);
    }

    @Test
    @DisplayName("When createGame Then Return created Game")
    void whenCreateGameThenReturnGameCreatedGame(){
        //Arrange
        Long id = 1L;
        Game game = new Game();
        game.setGameId(id);

        when(gameRepository.save(game))
                .thenReturn(game);
        //Act
        Game foundGame = gameService.createGame(game);
        //Assert
        assertThat(foundGame.getGameId()).isEqualTo(id);
    }

    @Test
    @DisplayName("When updateGame Then Return updated Game")
    void whenUpdateGameThenReturnUpdatedGame(){
        //Arrange
        Long id = 1L;
        Game game = new Game();
        game.setGameId(id);

        String updateName = "updateName";
        String updateDescription = "updateDescription";
        Game updateGame = new Game();
        updateGame.setGameName(updateName);
        updateGame.setDescription(updateDescription);

        when(gameRepository.save(game))
                .thenReturn(game);
        when(gameRepository.findById(id))
                .thenReturn(Optional.of(game));
        //Act
        Game foundGame = gameService.createGame(game);
        gameService.updateGame(id, updateGame);
        //Assert
        assertThat(foundGame.getGameName()).isEqualTo(updateName);
    }

    @Test
    @DisplayName("When getGameById With Invalid Id Then Returns Resource Not Found Exception")
    void whenGetGameByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(gameRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template,"Game", "Id", id);
        //Act
        Throwable exception = catchThrowable(() -> {
            Game foundGame = gameService.getGameById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
    
}
