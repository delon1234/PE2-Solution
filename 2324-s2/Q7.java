import java.util.Comparator;

class Q7<T extends Fruit> implements Comparator<FruitStall<? extends T>> {
  // Compare which stall has the shorter day to expiry among all their fruits
  @Override
  public int compare(FruitStall<? extends T> stall1, FruitStall<? extends T> stall2) {
    return stall1.getFruits().stream()
        .map(Fruit::getDaysToExpiry)
        .reduce(Integer.MAX_VALUE, Math::min)
      - stall2.getFruits().stream()
        .map(Fruit::getDaysToExpiry)
        .reduce(Integer.MAX_VALUE, Math::min);
  }
}
