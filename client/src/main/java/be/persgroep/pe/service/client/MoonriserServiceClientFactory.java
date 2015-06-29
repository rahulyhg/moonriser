package be.persgroep.pe.service.client;

public abstract class MoonriserServiceClientFactory {
    public static MoonriserServiceClient createMoonriserServiceClientFactory(final MoonriserEnvironment moonriserEnvironment) {
        return new MoonriserServiceClient(
                moonriserEnvironment.getServerUrl() + "/be/",
                moonriserEnvironment.getServerUrl() + "/nl/");
    }
}