import java.util.List;

public class Station {
  private final String name;
  private final int openingYear;
  private final List<String> busConnections;

  public Station(String name, int openingYear, List<String> busConnections) {
    this.name = name;
    this.openingYear = openingYear;
    this.busConnections = busConnections;
  }

  public String getName() {
    return name;
  }

  public int getOpeningYear() {
    return openingYear;
  }

  public List<String> getBusConnections() {
    return busConnections;
  }

  @Override
  public String toString() {
    return name + " (" + openingYear + ")";
  }
}
