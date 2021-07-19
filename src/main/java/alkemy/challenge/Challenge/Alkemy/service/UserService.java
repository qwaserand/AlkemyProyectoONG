package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.exception.UserAlreadyExistException;
import alkemy.challenge.Challenge.Alkemy.model.User;
import alkemy.challenge.Challenge.Alkemy.repository.RoleRepository;
import alkemy.challenge.Challenge.Alkemy.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Optional<User> findById(Long id) {
        return userRepository.findByIdAndDeletedFalse(id);
    }

    public void softDelete(User user) {
        user.setDeleted(true);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty())
        {
            throw new UsernameNotFoundException("No se encontro el usuario con mail: "+ email);
        }
        return (UserDetails) userRepository.findByEmail(email).get();
    }

    /*public String saveUser(User user) throws UserAlreadyExistException {

        //boolean userExist = userRepository.findByEmail(user.getEmail()).isPresent();
        boolean userExist = false;

        if (userExist) {
            throw new UserAlreadyExistException("El usuario ya existe en la Base de Datos");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        String token = UUID.randomUUID().toString(); //Se crea un token generado aleatoriamente y se retorna el mismo.

        return token;
    }*/

    public String saveUser(User user) throws UserAlreadyExistException {

        if ((userRepository.findByEmail(user.getEmail())).isPresent())
            throw new UserAlreadyExistException("El usuario ya existe en la Base de Datos");

        user.setRole(roleRepository.findByName("ROLE_USER"));

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        String token = UUID.randomUUID().toString(); //Se crea un token generado aleatoriamente y se retorna el mismo.

        return token;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
