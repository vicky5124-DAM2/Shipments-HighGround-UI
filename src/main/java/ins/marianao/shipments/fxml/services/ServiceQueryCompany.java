package ins.marianao.shipments.fxml.services;

import cat.institutmarianao.shipmentsws.model.Company;
import ins.marianao.shipments.fxml.manager.ResourceManager;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class ServiceQueryCompany extends ServiceQueryBase<Company> {
	public static final String PATH_REST_OFFICES = "companies";

	@Override
	protected List<Company> customCall() throws Exception {
		Client client = ResourceManager.getInstance().getWebClient();

		WebTarget webTarget = client.target(ResourceManager.getInstance().getParam("web.service.host.url")).path(PATH_REST_OFFICES).path(PATH_QUERY_ALL);

		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);

		List<Company> companies = new ArrayList<>();

		try {
			Response response = invocationBuilder.get();

			if (response.getStatus() != Response.Status.OK.getStatusCode()) 
				throw new Exception(ResourceManager.getInstance().responseErrorToString(response));

			companies = response.readEntity(new GenericType<List<Company>>(){});
			
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
		
		return companies;
	}
}
