import java.util.List;

public class MRT {

  // 2a. All distinct connected bus station names
  public static List<String> getAllBusConnections(List<Station> stations) {
    return stations.stream().flatMap(s -> s.getBusConnections().stream()).distinct().toList();
  }

  // 2b. Stations opened before the given year
  public static List<String> getOldStations(List<Station> stations, int year) {
    return stations.stream().filter(s -> s.getOpeningYear() < year).map(Station::getName).toList();
  }

  // 2c. Top 3 most connected stations (by bus)
  public static List<String> getTopConnectedStations(List<Station> stations) {
    return stations.stream()
        .sorted(
            (a, b) -> Integer.compare(b.getBusConnections().size(), a.getBusConnections().size()))
        .limit(3)
        .map(Station::getName)
        .toList();
  }

  // 2d. Bus connections starting with a specific prefix
  public static List<String> getBusConnectionsStartingWith(List<Station> stations, String prefix) {
    return stations.stream()
        .flatMap(s -> s.getBusConnections().stream())
        .filter(name -> name.startsWith(prefix))
        .distinct()
        .toList();
  }

  // 2e. Check if a specific bus number is available at a given station.
  public static boolean isBusAvailableAtStation(
      List<Station> stations, String station, String bus) {
    return stations.stream()
        .filter(s -> s.getName().equals(station))
        .flatMap(s -> s.getBusConnections().stream())
        .anyMatch(name -> name.equals(bus));
  }
}
