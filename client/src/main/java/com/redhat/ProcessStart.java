package com.redhat;

import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.ProcessServicesClient;

import java.util.HashMap;
import java.util.Map;

public class ProcessStart {
	private static final String ENDPOINT = "http://kie-app-cruyff-ips-training.apps.latest.xpaas/kie-server/services/rest/server";
	private static final String USERNAME = "kieserver";
	private static final String PASSWORD = "kieserver1!";

	public static void main(String[] args) {
		KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(ENDPOINT, USERNAME, PASSWORD);

		KieServicesClient client = KieServicesFactory.newKieServicesClient(config);

		final ProcessServicesClient processServicesClient = client.getServicesClient(ProcessServicesClient.class);

		Map<String, Object> processArgs = new HashMap<>();
		processArgs.put("age", Integer.parseInt(args[0]));
		processArgs.put("amount", Integer.parseInt(args[1]));
		processArgs.put("income", Integer.parseInt(args[2]));

		Long processInstanceId = processServicesClient.startProcess("LoanApproval", "LoanApproval.Application", processArgs);

		System.out.println("Started process instance: " + processInstanceId);
	}
}
