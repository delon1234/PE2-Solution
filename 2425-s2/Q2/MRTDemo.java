import java.util.List;

public class MRTDemo {
  public static void main(String[] args) {
    List<Station> sampleStations =
        List.of(
            new Station("Bugis", 1985, List.of("Bus 7", "Bus 36", "Bus 133")),
            new Station("Bayfront", 2012, List.of("Bus 97", "Bus 106")),
            new Station("Bedok", 1989, List.of("Bus 2", "Bus 9", "Bus 168")),
            new Station("Boon Lay", 1990, List.of("Bus 30", "Bus 172", "Bus 180")),
            new Station("Dhoby Ghaut", 1987, List.of("Bus 14", "Bus 16", "Bus 190")));

    System.out.println("All Bus Connections: " + MRT.getAllBusConnections(sampleStations));
    System.out.println("Stations opened before 1987: " + MRT.getOldStations(sampleStations, 1987));
    System.out.println("Stations opened before 1990: " + MRT.getOldStations(sampleStations, 1990));

    System.out.println("Top Connected Stations: " + MRT.getTopConnectedStations(sampleStations));
    System.out.println(
        "Bus Connections Starting with 'Bus 1': "
            + MRT.getBusConnectionsStartingWith(sampleStations, "Bus 13"));
    System.out.println(
        "Bus Connections Starting with 'Bus 9': "
            + MRT.getBusConnectionsStartingWith(sampleStations, "Bus 9"));

    System.out.println(
        "Is Bus 36 available at Bugis? "
            + MRT.isBusAvailableAtStation(sampleStations, "Bugis", "Bus 36"));

    System.out.println(
        "Is Bus 168 available at Bayfront? "
            + MRT.isBusAvailableAtStation(sampleStations, "Bayfront", "Bus 168"));
  }
}
