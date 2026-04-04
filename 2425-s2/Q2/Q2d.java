import java.util.List;

public class Q2d {
  public static List<List<Station>> getConnectedStationPairs(
      List<Station> stations, List<String> busNames) {
    return stations.stream()
        .flatMap(s1 -> stations.stream()
            .filter(s2 -> stations.indexOf(s1) < stations.indexOf(s2))
            .filter(s2 -> busNames.stream()
                  .anyMatch(bus -> s1.getBusConnections().contains(bus) && 
                                   s2.getBusConnections().contains(bus)))
            .map(s2 -> List.of(s1, s2)))
        .toList();
  }
}
