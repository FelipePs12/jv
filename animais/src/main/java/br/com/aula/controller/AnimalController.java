package br.com.aula.controller;// AnimalController.java
import br.com.aula.model.Animal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    private List<Animal> animais = new ArrayList<>();


    @PostMapping
    public ResponseEntity<Animal> cadastrarAnimal(@RequestBody Animal animal) {
        animais.add(animal);
        return new ResponseEntity<>(animal, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> alterarAnimal(@PathVariable int id, @RequestBody Animal novoAnimal) {
        for (int i = 0; i < animais.size(); i++) {
            Animal animal = animais.get(i);
            if (animal.getId() == id) {
                animais.set(i, novoAnimal);
                return new ResponseEntity<>(novoAnimal, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Animal não encontrado", HttpStatus.NOT_FOUND);
    }


    @GetMapping
    public ResponseEntity<List<Animal>> consultarAnimais() {
        return new ResponseEntity<>(animais, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> consultarAnimalPorId(@PathVariable int id) {
        for (Animal animal : animais) {
            if (animal.getId() == id) {
                return new ResponseEntity<>(animal, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Animal não encontrado", HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarAnimal(@PathVariable int id) {
        animais.removeIf(animal -> animal.getId() == id);
        return new ResponseEntity<>("Animal removido com sucesso", HttpStatus.OK);
    }


    @GetMapping("/peso")
    public ResponseEntity<Object> obterAnimaisExtremos() {
        if (animais.isEmpty()) {
            return new ResponseEntity<>("Não há animais cadastrados", HttpStatus.NOT_FOUND);
        }

        Animal animalMaisPesado = animais.stream().max((a1, a2) -> Double.compare(a1.getPeso(), a2.getPeso())).get();
        Animal animalMaisLeve = animais.stream().min((a1, a2) -> Double.compare(a1.getPeso(), a2.getPeso())).get();

        return new ResponseEntity<>("Animal mais pesado: " + animalMaisPesado.getNome() + ", Peso: " + animalMaisPesado.getPeso() +
                "\nAnimal mais leve: " + animalMaisLeve.getNome() + ", Peso: " + animalMaisLeve.getPeso(), HttpStatus.OK);
    }
}
