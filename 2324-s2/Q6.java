import java.util.List;

class Q6 {
  public static List<FruitStall<Fruit>> consolidateStallsbyType(
      List<? extends FruitStall<? extends Fruit>> stalls) {
    return Q5.findUniqueFruitTypes(stalls).stream() 
      .map(name -> stalls.stream()
          .map(stall -> new FruitStall<Fruit>(stall.findFruitsByName(name)))
          .reduce(new FruitStall<Fruit>(), (acc, stall) -> Q4.mergeStalls(acc, stall))
      ).toList();
  }
}
