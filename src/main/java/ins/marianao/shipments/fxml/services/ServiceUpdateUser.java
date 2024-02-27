package ins.marianao.shipments.fxml.services;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import cat.institutmarianao.shipmentsws.model.User;
import ins.marianao.shipments.fxml.manager.ResourceManager;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ServiceUpdateUser extends Service<User>{

    private static final String PATH_UPDATE = "update";

    private String username;
    private String password;
    private long companyId;
    private String companyName;
    private String role;
    private String fullName;
    private long extension;

    public ServiceUpdateUser(String username, String password, long companyId, String companyName, String role, String fullName, long extension) throws Exception {
        if (username.isBlank()) throw new Exception(ResourceManager.getInstance().getText("error.login.find.no.username"));
        if (password.isBlank()) throw new Exception(ResourceManager.getInstance().getText("error.login.find.no.password"));
        if (companyName.isBlank()) throw new Exception(ResourceManager.getInstance().getText("error.updateUser.find.no.companyName"));
        if (role.isBlank()) throw new Exception(ResourceManager.getInstance().getText("error.updateUser.find.no.role"));
        if (fullName.isBlank()) throw new Exception(ResourceManager.getInstance().getText("error.updateUser.find.no.fullName"));
        if (!(role.equals("RECEPCIONIST")||role.equals("LOGISTICS_MANAGER")||role.equals("COURIER"))) throw new Exception(ResourceManager.getInstance().getText("error.updateUser.find.wrong.role"));

        this.username = username;
        this.password = password;
        this.companyId = companyId;
        this.companyName = companyName;
        this.role = role;
        this.fullName = fullName;
        this.extension = extension;
    }

    @Override
    protected Task<User> createTask() {
        return new Task<User>() {
            @Override
            protected User call() throws Exception {
                Client client = ResourceManager.getInstance().getWebClient();

                WebTarget webTarget = client.target(ResourceManager.getInstance().getParam("web.service.host.url")).path(ServiceQueryUsers.PATH_REST_USERS).path(PATH_UPDATE);

                Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);

                // {"username":"alex","password":"1234"}
                JsonObject company = Json.createObjectBuilder()
                        .add("id", companyId)
                        .add("name", companyName)
                        .build();
                JsonObject newUser = Json.createObjectBuilder()
                        .add("username", username)
                        .add("password", password)
                        .add("role", role)
                        .add("fullName", fullName)
                        .add("extension", extension)
                        .add("company", company)
                        .build();

                User user = null;
                try {
                    Response response = invocationBuilder.put(Entity.entity(newUser, MediaType.APPLICATION_JSON));

                    if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                        throw new Exception(ResourceManager.getInstance().responseErrorToString(response));
                    }
                    user = response.readEntity(User.class);
                } catch (ResponseProcessingException e) {
                    e.printStackTrace();
                    throw new Exception(ResourceManager.getInstance().getText("error.service.response.processing")+" "+e.getMessage());
                } catch (ProcessingException e) {
                    e.printStackTrace();
                    throw new Exception(ResourceManager.getInstance().getText("error.service.processing")+" "+e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }

                return user;
            }
        };
    }

}
