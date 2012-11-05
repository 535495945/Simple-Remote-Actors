package le.yord.samples.java;

import com.typesafe.config.ConfigFactory;
import akka.kernel.Bootable;

import akka.actor.ActorSystem;
import akka.actor.Actor;
import akka.actor.Props;
import akka.actor.ActorRef;

public class PongDaemon implements Bootable {
  
  final ActorSystem system = ActorSystem.create("PongDaemon", ConfigFactory.load().getConfig("pongDaemon"));
  
  public static void main(String[] args) {
    new PongDaemon().startup();
  }
  
  public void startup() {
    system.actorOf(new Props(PongActor.class), "ponger");
    System.out.println("Started Pong Daemon!");
  }
  
  public void shutdown() {
    system.shutdown();
  }
  
}