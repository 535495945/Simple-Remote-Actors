package le.yord.samples.java;

import com.typesafe.config.ConfigFactory;
import akka.kernel.Bootable;

import akka.actor.ActorSystem;
import akka.actor.Actor;
import akka.actor.Props;
import akka.actor.ActorRef;

public class PingDaemon implements Bootable {
  
  final ActorSystem system = ActorSystem.create("PingDaemon", ConfigFactory.load().getConfig("pingDaemon"));
  
  public static void main(String[] args) {
    new PingDaemon().startup();
  }
  
  public void startup() {
    final ActorRef pinger = system.actorOf(new Props(PingActor.class), "pinger");
    System.out.println("Started Ping Daemon!");
    pinger.tell(new Start());
  }
  
  public void shutdown() {
    system.shutdown();
  }
  
}