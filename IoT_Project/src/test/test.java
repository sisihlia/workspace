package test;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.Message;
import org.eclipse.californium.core.coap.MessageObserverAdapter;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.core.network.EndpointManager;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.*;

//import javax.xml.ws.Response;

;

public class test {

	//private URI uri;
	
	public test(){
		
	}
	
	public void postConnection(String x) throws InterruptedException{
		
		Request request = new Request(Code.POST);
		request.setURI("coap://[aaaa::444f:4f50:a729:142c]:5683/pwr/rel");
		request.setPayload(x);
		request.send();
		org.eclipse.californium.core.coap.Response response = request.waitForResponse();
		
		
	}
	
	public void getValue() throws InterruptedException{
		
		Request request = new Request(Code.GET);
		request.setURI("coap://[aaaa::444f:4f50:a729:142c]:5683/pwr/w");
		request.send();
		org.eclipse.californium.core.coap.Response response = request.waitForResponse();
		System.out.println("Value" + response);
	}
	
	public void observeValue() throws InterruptedException{
		/*Request request = new Request(Code.GET);
		request.setURI("coap://[aaaa::444f:4f50:a729:142c]:5683/pwr/w");
		request.setObserve();
		
		request.addMessageObserver(new MessageObserverAdapter() {
			public void responded(Response response) {
				if (response.getCode() == ResponseCode.CONTENT)
				System.out.println("Received" + response.getPayloadString());
				
				
			}});
		
		request.send();*/
		CoapClient client = new CoapClient("coap://[aaaa::444f:4f50:a729:142c]:5683/pwr/w");
		System.out.println("SYNCHRONOUS");
		// synchronous
		String content1 = client.get().getResponseText();
		System.out.println("RESPONSE 1: " + content1);
		CoapResponse resp2 = client.post("payload", MediaTypeRegistry.TEXT_PLAIN);
		System.out.println("RESPONSE 2 CODE: " + resp2.getCode());

		// asynchronous
		
		System.out.println("ASYNCHRONOUS (press enter to continue)");
		client.get(new CoapHandler() {
			@Override public void onLoad(CoapResponse response) {
				String content = response.getResponseText();
				System.out.println("RESPONSE 3: " + content);
			}
			@Override public void onError() {
				System.err.println("FAILED");
			}
		});
		// wait for user
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try { br.readLine(); } catch (IOException e) { }
		// observe
		
		System.out.println("OBSERVE (press enter to exit)");
		CoapObserveRelation relation = client.observe(
				new CoapHandler() {
					@Override public void onLoad(CoapResponse response) {
						String content = response.getResponseText();
						System.out.println("Measured: " + content);
					}
					@Override public void onError() {
						System.err.println("OBSERVING FAILED (press enter to exit)");
					}
				});
		// wait for user
		try { br.readLine(); } catch (IOException e) { }
		System.out.println("CANCELLATION");
		relation.proactiveCancel();
	}
	
	
	public static void main(String args[]) throws InterruptedException {
		//mongodb mdb = new mongodb();
		test test = new test();
		test.postConnection("1");
		//test.getValue();
		test.observeValue();
		/*Request request = new Request(Code.POST);
		request.setURI("coap://[aaaa::444f:4f50:a729:142c]:5683/pwr/rel");
		request.setPayload("1");
		request.send();
		org.eclipse.californium.core.coap.Response response = request.waitForResponse();
		//Endpoint endpoint = EndpointManager.getEndpointManager().getDefaultEndpoint();
		//request.send(endpoint);*/
		
		
	}

	
}
