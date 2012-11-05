package le.yord.samples.java;

import akka.actor.UntypedActor;

public class PongActor extends UntypedActor {
  
  public void onReceive(Object message) throws Exception {
    if (message instanceof String) {
      final String string = (String) message;
      System.out.println("PongActor: Received String(\""+string+"\") message!");
      final String response = string.toUpperCase() + " pong";
      getSender().tell(response);
      System.out.println("PongActor: Send String(\""+response+"\") message!");
    } else if (message instanceof Stop) {
      System.out.println("PongActor: Received Stop message!");
      getContext().system().shutdown();
      System.out.println("Pong Daemon terminated!");
    }
  }
  
}