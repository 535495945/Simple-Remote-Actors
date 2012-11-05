package le.yord.samples.java;

import akka.actor.UntypedActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class PingActor extends UntypedActor {
  
  final ActorRef ponger = getContext().actorFor("akka://PongDaemon@127.0.0.1:5005/user/ponger");
  
  public void onReceive(Object message) throws Exception {
    if (message instanceof Start) {
      System.out.println("PingActor: Received Start message!");
      final String ping = "ping";
      ponger.tell(ping, getSelf());
      System.out.println("PingActor: Send String(\""+ping+"\") message!");
    } else if(message instanceof String) {
      final String string = (String) message;
      System.out.println("PingActor: Received String(\""+string+"\") message!");
      ponger.tell(new Stop());
      getContext().system().shutdown();
      System.out.println("Ping Daemon terminated!");
    }
  }
  
}