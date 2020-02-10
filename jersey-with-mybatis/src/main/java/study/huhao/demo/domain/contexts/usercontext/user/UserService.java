package study.huhao.demo.domain.contexts.usercontext.user;

import java.util.UUID;
import study.huhao.demo.domain.core.common.excpetions.EntityNotFoundException;
import study.huhao.demo.domain.core.concepts.Service;

public class UserService implements Service {

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User create(UUID id, String userName, String displayName, String signature, String email) {
    User user =  new User(id, userName, displayName, signature, email);
    userRepository.save(user);
    return user;
  }

  public User get(UUID id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(User.class, id));
  }
}
