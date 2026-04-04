import java.util.List;

public class Q2a {
  public static List<String> getAllBusConnections(List<Station> stations) {
    return stations.stream()
        .flatMap(s -> s.getBusConnections().stream())
        .distinct()
        .sorted()
        .toList();
  }
}
