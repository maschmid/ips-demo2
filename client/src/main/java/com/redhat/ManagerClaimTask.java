package com.redhat;

import org.kie.server.api.model.instance.TaskInstance;
import org.kie.server.api.model.instance.TaskSummary;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.UserTaskServicesClient;

import com.redhat.xpaas.qe.loanapproval.Application;

import java.io.Console;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ManagerClaimTask {
	private static final String ENDPOINT = "http://kie-app-cruyff-ips-training.apps.latest.xpaas/kie-server/services/rest/server";

	private static void processTask(final UserTaskServicesClient userTaskServicesClient, String username, TaskSummary taskSummary) {

		System.out.println("status: " +  taskSummary.getStatus());

		switch (taskSummary.getStatus()) {
			case "Ready": userTaskServicesClient.claimTask("LoanApproval", taskSummary.getId(), username);
			case "Reserved": userTaskServicesClient.startTask("LoanApproval", taskSummary.getId(), username);
			case "InProgress": break;
			default: throw new IllegalStateException("Unexpected task state " + taskSummary.getStatus());
		}

		TaskInstance ti = userTaskServicesClient.getTaskInstance("LoanApproval", taskSummary.getId(), true, true, false);
		Application application = (Application)ti.getInputData().get("application");

		readLine: for(;;) {
			switch (System.console().readLine("Approve " + application + "? (yes/no)")) {
				case "yes": application.setApproval(true); break readLine;
				case "no": application.setApproval(false); break readLine;
			}
		}

		Map<String, Object> outputMap = new HashMap<>();
		outputMap.put("application", application);
		userTaskServicesClient.completeTask("LoanApproval", taskSummary.getId(), username, outputMap);
	}

	public static void main(String[] args) {

		final String username = args[0];
		final String password = args[1];

		KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(ENDPOINT, username, password);

		Set<Class<?>> classes = new HashSet<>();
		classes.add(Application.class);
		config.addJaxbClasses(classes);

		KieServicesClient client = KieServicesFactory.newKieServicesClient(config);

		final UserTaskServicesClient userTaskServicesClient = client.getServicesClient(UserTaskServicesClient.class);

		for (TaskSummary taskSummary : userTaskServicesClient.findTasksOwned(username, 0, 50)) {
			processTask(userTaskServicesClient, username, taskSummary);
		}

		for (TaskSummary taskSummary : userTaskServicesClient.findTasksAssignedAsPotentialOwner(username, 0, 50)) {
			processTask(userTaskServicesClient, username, taskSummary);
		}
	}
}
