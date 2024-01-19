package testUtils;

public enum APIResources {

    CREATEBOARD("/1/boards/"),
    UPDATEBOARD("/1/boards/{id}"),
    GETBOARD("/1/boards/{id}"),
    DELETEBOARD("/1/boards/{id}");

    private String resource;

    APIResources(String resource)
    {
        this.resource=resource;
    }

    public String getResource()
    {
        return resource;
    }
}
