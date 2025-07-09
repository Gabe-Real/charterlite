package dev.gabereal.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class LesserDivinityScheduler {
	private static class ScheduledTask {
		int ticksLeft;
		Runnable task;

		ScheduledTask(int ticks, Runnable task) {
			this.ticksLeft = ticks;
			this.task = task;
		}
	}

	private static final Queue<ScheduledTask> tasks = new LinkedList<>();

	public static void init() {
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			Iterator<ScheduledTask> it = tasks.iterator();
			while (it.hasNext()) {
				ScheduledTask scheduledTask = it.next();
				scheduledTask.ticksLeft--;
				if (scheduledTask.ticksLeft <= 0) {
					scheduledTask.task.run();
					it.remove();
				}
			}
		});
	}

	public static void schedule(int delayTicks, Runnable task) {
		tasks.add(new ScheduledTask(delayTicks, task));
	}
}
