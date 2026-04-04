import java.util.List;

class Q5 {
  public static List<String> findUniqueFruitTypes(
      List<? extends FruitStall<? extends Fruit>> stalls) {
    return stalls.stream()
      .flatMap(stall -> stall.getFruits().stream())
      .map(Fruit::getName)
      .distinct()
      .sorted()
      .toList();
  }
}
