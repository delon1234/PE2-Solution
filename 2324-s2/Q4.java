import java.util.stream.Stream;

class Q4 {
  public static <U extends Fruit> FruitStall<U> mergeStalls(
      FruitStall<? extends U> stall1, FruitStall<? extends U> stall2) {
    return new FruitStall<>(
        Stream.concat(stall1.getFruits().stream(), stall2.getFruits().stream())
            .toList());
  }
}
