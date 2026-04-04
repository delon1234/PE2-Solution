import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FruitStall<T extends Fruit> {
  private final List<T> fruits;

  public FruitStall() {
    // this.fruits = new ArrayList<>(); 
    // or
    this.fruits = List.of();
  }

  public FruitStall(List<? extends T> fruits) {
    this.fruits = new ArrayList<>(fruits); 
    // or
    // @SuppressWarnings("unchecked")
    // List<T> temp = (List<T>) fruits;
    // this.fruits = temp;
  }

  public List<T> getFruits() {
    return this.fruits;
  }

  public List<T> findFruitsByName(String name) {
    return fruits.stream()
      .filter(fruit -> fruit.getName().equals(name))
      .collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return this.fruits.stream()
        .reduce("\n", (acc, fruit) -> acc + "- " + fruit + "\n", 
            (acc1, acc2) -> acc1 + acc2);
  }
}
