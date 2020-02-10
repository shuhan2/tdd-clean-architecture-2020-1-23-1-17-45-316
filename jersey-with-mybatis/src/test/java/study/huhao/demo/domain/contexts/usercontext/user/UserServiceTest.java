package study.huhao.demo.domain.contexts.usercontext.user;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import study.huhao.demo.domain.core.common.excpetions.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

  private UserRepository userRepository;

  private UserService userService;

  @BeforeEach
  void setUp() {
    userRepository = mock(UserRepository.class);
    userService = new UserService(userRepository);
  }

  @Nested
  class createBlog {

    @Test
    void should_create_correctly() {
      User createdUser = userService
          .create(UUID.randomUUID(), "Bob", "Solider", "Solider", "xx@email.com");

      verify(userRepository).save(any(User.class));
      assertThat(createdUser.getId()).isNotNull();
    }
  }

  @Nested
  class getUser {

    @Test
    void should_get_correctly() {
      User mockUser = mock(User.class);
      when(userRepository.findById(mockUser.getId())).thenReturn(Optional.of(mockUser));

      User user = userService.get(mockUser.getId());

      assertThat(user).isEqualTo(mockUser);
    }

    @Test
    void should_throw_EntityNotFoundException_when_blog_not_found() {
      User mockUser = mock(User.class);

      when(userRepository.findById(mockUser.getId())).thenReturn(Optional.empty());

      assertThatThrownBy(() -> userService.get(mockUser.getId()))
          .isInstanceOf(EntityNotFoundException.class)
          .hasMessage("cannot find the user with id " + mockUser.getId());
    }
  }
}