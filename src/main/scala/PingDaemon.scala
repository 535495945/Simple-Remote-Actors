package le.yord.samples.scala

import com.typesafe.config.ConfigFactory
import akka.kernel.Bootable

import akka.actor.{ ActorSystem, Actor, Props, ActorRef }

object PingDaemon extends Bootable {
  
  val system = ActorSystem("PingDaemon", ConfigFactory.load.getConfig("pingDaemon"))
  
  def main(args: Array[String]): Unit = {
    startup()
  }
  
  def startup(): Unit = {
    val pinger: ActorRef = system.actorOf(Props[PingActor], "pinger")
    println("Started Ping Daemon!")
    pinger ! Start
  }
  
  def shutdown(): Unit = {
    system.shutdown()
  }
  
}

class PingActor extends Actor {
  
  val pongActor: ActorRef = context.actorFor("akka://PongDaemon@127.0.0.1:5005/user/ponger")
  
  def receive = {
    case Start => {
      println("PingActor: Received Start message!")
      val ping = "ping"
      pongActor ! ping
      println("PingActor: Send String(\""+ping+"\") message!")
    }
    case string: String => {
      println("PingActor: Received String(\""+string+"\") message!")
      pongActor ! Stop
      context.system.shutdown()
      println("Ping Daemon terminated!")
    }
  }
}