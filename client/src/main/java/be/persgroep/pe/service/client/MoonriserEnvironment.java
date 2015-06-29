package be.persgroep.pe.service.client;

public enum MoonriserEnvironment {
    LOCAL("http://localhost:8080"),
    DEVELOPMENT("http://v1.perestservice.dev.persgroep.net/perestservice");
    //TEST("http://localhost:8080"),
    //STAGING("http://localhost:8080"),
    //PRODUCTION("http://localhost:8080");

    private MoonriserEnvironment(final String serverUrl) {
        this.serverUrl = serverUrl;
    }

    private final String serverUrl;

    public String getServerUrl() {
        return this.serverUrl;
    }
}
