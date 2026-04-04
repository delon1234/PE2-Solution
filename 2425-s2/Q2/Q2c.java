import java.util.Comparator;
import java.util.List;

public class Q2c {
  // Comparator to sort by number of connections (desc), then name (asc)
  private static final Comparator<Station> stationComparator =
      Comparator.comparingInt((Station s) -> s.getBusConnections().size())
          .reversed()
          .thenComparing(Station::getName);

  // 2c. Top 3 most connected stations (by bus)
  public static List<String> getTopConnectedStations(List<Station> stations) {
    return stations.stream()
      .sorted(stationComparator)
      .limit(3)
      .map(Station::getName)
      .toList();
  }
}
