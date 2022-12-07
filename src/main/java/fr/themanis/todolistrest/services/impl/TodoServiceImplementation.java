package fr.themanis.todolistrest.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.themanis.todolistrest.entities.TodoEntity;
import fr.themanis.todolistrest.models.TodoCoordinate;
import fr.themanis.todolistrest.models.TodoModel;
import fr.themanis.todolistrest.models.TodoServiceException;
import fr.themanis.todolistrest.repositories.TodoRepository;
import fr.themanis.todolistrest.services.TodoService;
import jakarta.annotation.PostConstruct;

@Service
public class TodoServiceImplementation implements TodoService {
  @Autowired
  private TodoRepository todoRepo;

  private TodoModel toModel(TodoEntity entity) {
    return new TodoModel(entity.getId(), entity.getLabel(), entity.getDueDate(), entity.isDone(), isRemovable(entity),
        getPosition(entity));
  }

  private TodoCoordinate getPosition(TodoEntity entity) {
    TodoCoordinate position = null;
    if (entity.getLatitude() != null && entity.getLongitude() != null)
      position = new TodoCoordinate(entity.getLatitude(), entity.getLongitude());
    return position;
  }

  private boolean isRemovable(TodoEntity entity) {
    return entity.isDone() && entity.getDueDate().getTime() < new Date().getTime();
  }

  @PostConstruct
  private void addTodoExample() {
    if (todoRepo.count() == 0) {
      List<TodoEntity> entites = new ArrayList<>();
      List<String> descriptions = Arrays.asList("Partir en sac à dos dans un endroit inconnu",
          "Trouver le boulot qui te plait", "Adopter un chien", "Voir un opéra", "Faire un tour en Mongolfière",
          "Tomber amoureux", "Aller à center Park", "Avoir son permis", "Faire de la plongée",
          "Réussir à faire vivre ses plantes", "Faire une thalasso", "Apprendre à danser le rock", "Aller au Japon",
          "Savoir cuisiner 30 plats", "Avoir un bonzaï", "Apprendre à jouer du piano", "Aller dans un parc aquatique",
          "Faire un shooting photo", "Partir au dernier moment dans un endroit inconnu", "Voir des aurores boréales",
          "Dormir sous les étoiles", "Trouver l’amour de sa vie", "Faire le nouvel an chinois en chine",
          "Apprendre à faire du surf", "Ecrire un livre", "Apprendre à dessiner", "Voir un ballet",
          "Faire un bain de minuit", "Aller à Londres", "Faire du kayak", "Partir en colo",
          "Faire une retraite de yoga", "Faire du ski", "Se baigner sous une cascade naturelle",
          "Etre bilingue en anglais", "Donner une conférence", "Voir des rizières", "Se baigner dans la mer morte",
          "Faire une balade en cheval", "Aller à Rio pendant le carnaval", "Courir le marathon de Paris",
          "Poser un pied sur chaque continent", "Faire du stop", "Découvrir le parc de Yellowstone",
          "Voir le mont blanc", "Participer à un enterrement de vie de célibataire", "Prendre des cours de pole danse",
          "Tester le théatre", "Essayer de manger bio et local", "Avoir réellement confiance en soi",
          "Tester les thermes en Hongrie", "Dormir dans une cabane dans les arbres", "Faire un service civique (lien)",
          "Avoir un aquarium", "Inviter ses parents au restaurant", "Nager avec des tortues",
          "Voir les pyramides en Egypte", "Admirer un coucher de soleil depuis la dune du pilât",
          "Partir en Erasmus (info)", "Gagner une compétition", "Faire la paix avec son corps", "Devenir végétarien",
          "Devenir figurant dans un film", "Participer à une émission TV", "Voir un Volcan",
          "Assister aux Jeux olympique", "Gagner un concours", "Dormir dans une maison bulle",
          "Diner dans un restaurant gastronomique", "Nager avec les tortues", "Changer de couleur de cheveux",
          "Voir un spectacle à Broadway", "Faire un safari", "Fêter Thanksgiving",
          "Aider une personne (idées sur ce lien)", "Voyager seul", "Faire un saut en parachute",
          "Aller dans le désert", "Se perdre dans un labyrinthe",
          "Faire une randonnée avec un magnifique paysage à la clef", "Se balader dans les champs de vignes",
          "Essayer l’acuponcture", "Manger dans un restaurant sous l’eau", "Aller à Venise en amoureux",
          "Faire une croisière", "Faire un voyage humanitaire", "Vivre en colocation",
          "Apprendre à jouer de la guitare", "Parrainer un enfant", "Aller voir une voyante", "Voir une étoile filante",
          "Avoir visité plus de 10 pays", "Participer à une full moon party", "Créer son blog",
          "Vaincre une de ces peurs", "Visiter une grotte", "Découvrir le marché de Noel à Strasbourg",
          "Faire un massage à 4 mains", "Apprécier pleinement les moments de bonheur");
      Random rd = new Random();
      for (String desc : descriptions) {
        TodoEntity todo = new TodoEntity();

        todo.setLabel(desc);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, rd.nextInt(300) - 150);
        todo.setDueDate(cal.getTime());

        todo.setCreatedAt(new Date());
        todo.setUpdatedAt(new Date());

        todo.setDone(rd.nextBoolean());

        if (rd.nextBoolean()) {
          todo.setLatitude(rd.nextDouble() * 180 - 90);
          todo.setLongitude(rd.nextDouble() * 360 - 1800);
        }
        entites.add(todo);
      }
      todoRepo.saveAll(entites);
    }
  }

  @Override
  public List<TodoModel> findAll() {
    return this.todoRepo.findAll().stream().map(e -> toModel(e)).collect(Collectors.toList());
  }

  @Override
  public List<TodoModel> search(String keyword) throws TodoServiceException {
    if (keyword == null || keyword.trim().length() == 0)
      throw new TodoServiceException(12, "le mot-clé est obligatoire pour effectuer une recherche");
    return this.todoRepo.findByLabelContaining(keyword).stream().map(e -> toModel(e)).collect(Collectors.toList());
  }

  @Override
  public TodoModel findOne(int id) throws TodoServiceException {
    Optional<TodoEntity> entity = todoRepo.findById(id);
    if (entity.isEmpty())
      throw new TodoServiceException(11, "la tâche n°" + id + " est introuvable");
    return toModel(entity.get());
  }

}
