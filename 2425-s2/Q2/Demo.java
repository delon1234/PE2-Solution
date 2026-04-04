import java.util.List;

public class Demo {
  public static void main(String[] args) {
    List<Station> sampleStations =
        List.of(
            new Station("Bugis", 1985, List.of("Bus 7", "Bus 97", "Bus 133")),
            new Station("Bayfront", 2012, List.of("Bus 97", "Bus 106")),
            new Station("Bedok", 1989, List.of("Bus 2", "Bus 30", "Bus 168")),
            new Station("Boon Lay", 1990, List.of("Bus 30", "Bus 172", "Bus 180")),
            new Station("Dhoby Ghaut", 1987, List.of("Bus 14", "Bus 16", "Bus 190", "Bus 200")));

    System.out.println("== Q2a ==");
    System.out.println("All Bus Connections: " + Q2a.getAllBusConnections(sampleStations));

    System.out.println("== Q2b ==");
    System.out.println("Stations opened before 1987: " + Q2b.getOldStations(sampleStations, 1987));
    System.out.println("Stations opened before 1990: " + Q2b.getOldStations(sampleStations, 1990));

    System.out.println("== Q2c ==");
    System.out.println("Top Connected Stations: " + Q2c.getTopConnectedStations(sampleStations));

    System.out.println("== Q2d ==");
    System.out.println("Station pairs connected by Bus 30 and Bus 97:");
    List<String> targetBuses1 = List.of("Bus 30", "Bus 97");
    List<List<Station>> connectedPairs1 =
        Q2d.getConnectedStationPairs(sampleStations, targetBuses1);
    System.out.println(connectedPairs1);

    System.out.println("Station pairs connected by Bus 190:");
    List<String> targetBuses2 = List.of("Bus 190");
    List<List<Station>> connectedPairs2 =
        Q2d.getConnectedStationPairs(sampleStations, targetBuses2);
    System.out.println(connectedPairs2);
  }
}
