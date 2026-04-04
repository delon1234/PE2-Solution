import java.util.List;

class Q8 {
  public static <U extends Fruit> List<FruitStall<U>> sortByShortestExpiry(
      List<FruitStall<? extends U>> stalls) {
    return stalls.stream()
      .map(stall -> new FruitStall<U>(stall.getFruits()))
      .sorted(new Q7<>())
      .toList();
  }
}
