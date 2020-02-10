package study.huhao.demo.domain.contexts.usercontext.user;

import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

  @Nested
  class constructor {

    @Test
    void should_initialize_correctly() {
      UUID id = UUID.randomUUID();

      User user = new User(id, "Bob", "Solider", "Solider", "xx@email.com");

      assertThat(user.getId()).isNotNull();
      assertThat(user.getUserName()).isEqualTo("Bob");
      assertThat(user.getDisplayName()).isEqualTo("Solider");
      assertThat(user.getSignature()).isEqualTo("Solider");
      assertThat(user.getEmail()).isEqualTo("xx@email.com");
    }
  }

}