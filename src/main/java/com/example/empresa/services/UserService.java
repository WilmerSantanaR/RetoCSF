package com.example.empresa.services;

import com.example.empresa.model.User;
import com.example.empresa.model.Notification;
import com.example.empresa.repository.UserRepository;
import com.example.empresa.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public User registerUser(User user) {
        // Validación básica para asegurar que el username y password no sean nulos
        if (user.getUsername() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Username and password are required");
        }

        // Validación adicional si es necesario
        // Por ejemplo, asegurarse de que el usuario no exista previamente

        // Establecer rol de cliente por defecto si no se proporciona
        if (user.getRoleId() == null) {
            user.setRoleId(0);
        }

        return userRepository.save(user);
    }

    public List<User> getUsersByRole(int roleId) {
        return userRepository.findByRoleId(roleId);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null); // Retorna el usuario encontrado o null si no existe
    }

    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getUserId()).orElse(null);
        if (existingUser != null) {
            // Actualizar solo campos necesarios
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            existingUser.setEnabled(user.isEnabled());
            existingUser.setRoleId(user.getRoleId());
            return userRepository.save(existingUser);
        }
        return null; // Manejar caso de usuario no encontrado
    }

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // Manejar caso de autenticación fallida
    }

    public List<Notification> getNotificationsByUserOrderByCreatedAtDesc(User user) {
        return notificationRepository.findByUserOrderByCreatedAtDesc(user);
    }
}
