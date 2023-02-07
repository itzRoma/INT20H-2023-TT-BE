package org.sevenorganization.int20h2023ttbe.service;

import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.domain.entity.Ingredient;
import org.sevenorganization.int20h2023ttbe.domain.entity.Meal;
import org.sevenorganization.int20h2023ttbe.domain.entity.User;
import org.sevenorganization.int20h2023ttbe.exception.entity.EntityExistsException;
import org.sevenorganization.int20h2023ttbe.exception.entity.EntityNotFoundException;
import org.sevenorganization.int20h2023ttbe.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MealService mealService;
    private final IngredientService ingredientService;

    @Transactional
    public User saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityExistsException("Email is already taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw EntityNotFoundException.withId(User.class, id);
        });
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> {
            throw new EntityExistsException("Email is already taken");
        });
    }

    @Transactional
    public User updateUser(User target, User source) {
        if (userRepository.existsByEmail(source.getEmail()) && !target.getEmail().equals(source.getEmail())) {
            throw new EntityExistsException("Email is already taken");
        }
        BeanUtils.copyProperties(source, target, "id", "password");
        return userRepository.save(target);
    }

    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void saveMeal(Meal meal, User user) {
        Meal toAdd = mealService.existsByExternalIdInDB(meal.getExternalId())
                ? mealService.findMealByExternalIdFromDB(meal.getExternalId())
                : meal;
        user.getSavedMeals().add(toAdd);
        userRepository.save(user);
    }

    public void deleteMeal(Meal meal, User user) {
        user.getSavedMeals().remove(meal);
        userRepository.save(user);
    }

    public void saveIngredient(Ingredient ingredient, User user) {
        Ingredient toAdd = ingredientService.existsByExternalIdInDB(ingredient.getExternalId())
                ? ingredientService.findIngredientByExternalIdFromDB(ingredient.getExternalId())
                : ingredient;
        user.getSavedIngredients().add(toAdd);
        userRepository.save(user);
    }

    public void deleteIngredient(Ingredient ingredient, User user) {
        user.getSavedIngredients().remove(ingredient);
        userRepository.save(user);
    }
}
