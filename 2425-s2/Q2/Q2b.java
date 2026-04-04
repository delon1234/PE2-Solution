import java.util.List;

public class Q2b {
  public static List<String> getOldStations(List<Station> stations, int year) {
    return stations.stream()
      .filter(s -> s.getOpeningYear() < year)
      .map(Station::getName)
      .toList();
  }
}
