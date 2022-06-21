package com.telegaming.helpi;


import com.telegaming.helpi.domain.model.Community;
import com.telegaming.helpi.domain.repository.CommunityRepository;
import com.telegaming.helpi.domain.repository.PlayerRepository;
import com.telegaming.helpi.domain.service.CommunityService;
import com.telegaming.helpi.exception.ResourceNotFoundException;
import com.telegaming.helpi.service.CommunityServiceImpl;
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
class CommunityServiceImplTest {

    @MockBean
    private CommunityRepository communityRepository;

    @MockBean
    private PlayerRepository playerRepository;

    @Autowired
    private CommunityService communityService;

    @TestConfiguration
    static class CommunityServiceImplTestConfiguration{
        @Bean
        public CommunityService communityService(){
            return new CommunityServiceImpl();
        }
    }

    @Test
    @DisplayName("When getCommunityById With Valid Id Then Return Community")
    void whenGetCommunityByIdWithValidIdThenReturnCommunity(){
        //Arrange
        Long id = 1L;
        Community community = new Community();
        community.setCommunityId(id);
        when(communityRepository.findById(id))
                .thenReturn(Optional.of(community));
        //Act
        Community foundCommunity = communityService.getCommunityById(id);
        //Assert
        assertThat(foundCommunity.getCommunityId()).isEqualTo(id);
    }

    @Test
    @DisplayName("When createCommunity Then Return created Community")
    void whenCreateCommunityThenReturnCommunityCreatedCommunity(){
        //Arrange
        Long id = 1L;
        Community community = new Community();
        community.setCommunityId(id);

        when(communityRepository.save(community))
                .thenReturn(community);
        //Act
        Community foundCommunity = communityService.createCommunity(community);
        //Assert
        assertThat(foundCommunity.getCommunityId()).isEqualTo(id);
    }

    @Test
    @DisplayName("When updateCommunity Then Return updated Community")
    void whenUpdateCommunityThenReturnUpdatedCommunity(){
        //Arrange
        Long id = 1L;
        Community community = new Community();
        community.setCommunityId(id);

        String updateTitle = "updateTitle";
        String updateDescription = "updateDescription";
        Community updateCommunity = new Community();
        updateCommunity.setCommunityTitle(updateTitle);
        updateCommunity.setCommunityDescription(updateDescription);

        when(communityRepository.save(community))
                .thenReturn(community);
        when(communityRepository.findById(id))
                .thenReturn(Optional.of(community));
        //Act
        Community foundCommunity = communityService.createCommunity(community);
        communityService.updateCommunity(id, updateCommunity);
        //Assert
        assertThat(foundCommunity.getCommunityTitle()).isEqualTo(updateTitle);
    }

    @Test
    @DisplayName("When getCommunityById With Invalid Id Then Returns Resource Not Found Exception")
    void whenGetCommunityByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(communityRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template,"Community", "Id", id);
        //Act
        Throwable exception = catchThrowable(() -> {
            Community foundCommunity = communityService.getCommunityById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }


}
