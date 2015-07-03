package test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

public class observe {
	public static void main(String[] args) {
		test test = new test();
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
						System.out.println("NOTIFICATION: " + content);
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
}
