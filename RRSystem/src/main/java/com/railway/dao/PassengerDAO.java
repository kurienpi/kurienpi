public class PassengerDAO {

    private SessionFactory sessionFactory;

    // Constructor to initialize SessionFactory
    public PassengerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Fetches the list of senior citizens from the database.
     * @return List of SeniorCitizen objects.
     */
    public List<SeniorCitizen> getSeniorCitizens() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM SeniorCitizen", SeniorCitizen.class).list();
        }
    }

    /**
     * Fetches passengers by the specified coach type.
     * @param coachType the type of coach.
     * @return List of Passenger objects matching the coach type.
     */
    public List<Passenger> getPassengersByCoachType(String coachType) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Passenger p WHERE p.coachType = :coachType", Passenger.class)
                    .setParameter("coachType", coachType)
                    .list();
        }
    }

    /**
     * Fetches passengers by their destination.
     * @param destination the destination city.
     * @return List of Passenger objects matching the destination.
     */
    public List<Passenger> getPassengersByDestination(String destination) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Passenger p WHERE p.destination = :destination", Passenger.class)
                    .setParameter("destination", destination)
                    .list();
        }
    }
}
