package com.redhat;

import org.kie.server.api.model.instance.TaskSummary;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.UserTaskServicesClient;

import com.redhat.xpaas.qe.loanapproval.Application;

import java.util.HashSet;
import java.util.Set;

public class UserTasksList {
	private static final String ENDPOINT = "http://kie-app-cruyff-ips-training.apps.latest.xpaas/kie-server/services/rest/server";

	public static void main(String[] args) {

		final String username = args[0];
		final String password = args[1];

		KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(ENDPOINT, username, password);

		Set<Class<?>> classes = new HashSet<>();
		classes.add(Application.class);
		config.addJaxbClasses(classes);

		KieServicesClient client = KieServicesFactory.newKieServicesClient(config);

		final UserTaskServicesClient userTaskServicesClient = client.getServicesClient(UserTaskServicesClient.class);

		System.out.println(username + "'s tasks:");
		for (TaskSummary taskSummary : userTaskServicesClient.findTasks(username, 0, 50)) {
			System.out.println("  task: " + taskSummary.getName() + " owned by: " + taskSummary.getActualOwner() + " state: " + taskSummary.getStatus());
		}

		System.out.println(username + " as potential owner: ");
		for (TaskSummary taskSummary : userTaskServicesClient.findTasksAssignedAsPotentialOwner(username, 0, 50)) {
			System.out.println("  task: " + taskSummary.getName() + " owned by: " + taskSummary.getActualOwner() + " state: " + taskSummary.getStatus());
		}
	}
}
