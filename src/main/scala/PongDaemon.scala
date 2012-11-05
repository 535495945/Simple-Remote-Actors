package le.yord.samples.scala

import com.typesafe.config.ConfigFactory
import akka.kernel.Bootable

import akka.actor.{ ActorSystem, Actor, Props, ActorRef }

object PongDaemon extends Bootable {
  
  val system = ActorSystem("PongDaemon", ConfigFactory.load.getConfig("pongDaemon"))
  
  def main(args: Array[String]): Unit = {
    startup()
  }
  
  def startup(): Unit = {
    val ponger: ActorRef = system.actorOf(Props[PongActor], "ponger")
    println("Started Pong Daemon!")
  }
  
  def shutdown(): Unit = {
    system.shutdown()
  }
  
}

class PongActor extends Actor {
  
  def receive = {
    case string: String => {
      println("PongActor: Received String(\""+string+"\") message!")
      val response = string.toUpperCase + " pong"
      sender ! response
      println("PongActor: Send String(\""+response+"\") message!")
    }
    case Stop => {
      println("PongActor: Received Stop message!")
      context.system.shutdown()
      println("Pong Daemon terminated!")
    }
  }
}