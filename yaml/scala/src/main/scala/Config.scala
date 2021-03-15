import net.jcazevedo.moultingyaml._
import net.jcazevedo.moultingyaml.DefaultYamlProtocol._

object model {
  case class Config(
      nmesos_version: String,
      common: Environment,
      environments: Map[String, Environment]
  )

  case class Environment(
      resources: Option[Resources],
      container: Option[Container],
      executor: Option[ExecutorConf],
      singularity: Option[SingularityConf],
      after_deploy: Option[AfterDeployConf]
  )

  case class Resources(
      cpus: Double,
      memoryMb: Int,
      instances: Option[Int]
  )

  case class PortMap(
      containerPort: Int,
      hostPort: Option[Int],
      protocols: Option[String]
  )

  case class Container(
      image: String,
      command: Option[String],
      forcePullImage: Option[Boolean],
      ports: Option[Seq[PortMap]],
      labels: Option[Map[String, String]],
      env_vars: Option[Map[String, String]],
      volumes: Option[Seq[String]],
      network: Option[String],
      dockerParameters: Option[Map[String, String]],
      deploy_freeze: Option[Boolean]
  )

  case class SingularityConf(
      url: String,
      schedule: Option[String],
      requestType: Option[String],
      deployInstanceCountPerStep: Option[Int],
      deployStepWaitTimeMs: Option[Int],
      deployHealthTimeoutSeconds: Option[Int],
      autoAdvanceDeploySteps: Option[Boolean],
      healthcheckUri: Option[String],
      healthcheckPortIndex: Option[Int],
      healthcheckMaxRetries: Option[Int],
      healthcheckTimeoutSeconds: Option[Int],
      healthcheckMaxTotalTimeoutSeconds: Option[Int],
      requiredRole: Option[String],
      requiredAttributes: Option[Map[String, String]],
      allowedSlaveAttributes: Option[Map[String, String]],
      slavePlacement: Option[String]
  )

  case class ExecutorConf(
      customExecutorCmd: Option[String],
      env_vars: Option[Map[String, String]]
  )

  case class AfterDeployConf(
      on_success: List[DeployJob],
      on_failure: Option[DeployJob]
  )

  case class DeployJob(service_name: String, tag: Option[String])
}

object ConfigYamlProtocol extends DefaultYamlProtocol {
  import model._

  def parsePortMap(portMap: String, protocols: Option[String]): PortMap = {
    val (containerPort, hostPort) =
      portMap.split(":").map(_.toInt).toList match {
        case containerPort :: Nil             => (containerPort, None)
        case containerPort :: hostPort :: Nil => (containerPort, Some(hostPort))
        case _ =>
          throw new RuntimeException(
            "Failed to deserialize the port map specification"
          )
      }

    PortMap(containerPort, hostPort, protocols)
  }

  implicit val PortMapYamlFormat: YamlFormat[PortMap] =
    new YamlFormat[PortMap] {

      override def read(yaml: YamlValue): PortMap =
        yaml match {
          case YamlNumber(_) => PortMap(yaml.convertTo[Int], None, None)
          case YamlString(_) =>
            yaml.convertTo[String].split("/").toList match {
              case portMap :: Nil => parsePortMap(portMap, None)
              case portMap :: protocols :: Nil =>
                parsePortMap(portMap, Option(protocols))
              case _ =>
                throw new RuntimeException(
                  "Failed to deserialize the port map specification"
                )
            }
          case _ =>
            throw new RuntimeException(
              "Failed to deserialize the port specification"
            )
        }

      override def write(portMap: PortMap): YamlValue = {
        portMap.protocols match {
          case None =>
            portMap.hostPort match {
              case Some(hostPort) =>
                YamlString(s"${portMap.containerPort}:${hostPort}")
              case None => YamlNumber(portMap.containerPort)
            }
          case Some(protocols) =>
            portMap.hostPort match {
              case Some(hostPort) =>
                YamlString(s"${portMap.containerPort}:${hostPort}/${protocols}")
              case None => YamlString(s"${portMap.containerPort}/${protocols}")
            }
        }
      }
    }

  implicit val resourcesFormat: YamlFormat[Resources] = yamlFormat3(
    Resources.apply
  )
  implicit val containerFormat: YamlFormat[Container] = yamlFormat10(
    Container.apply
  )
  implicit val singularityFormat: YamlFormat[SingularityConf] = yamlFormat16(
    SingularityConf.apply
  )
  implicit val executorFormat: YamlFormat[ExecutorConf] = yamlFormat2(
    ExecutorConf.apply
  )
  implicit val deployJobFormat: YamlFormat[DeployJob] = yamlFormat2(
    DeployJob.apply
  )
  implicit val afterDeployFormat: YamlFormat[AfterDeployConf] = yamlFormat2(
    AfterDeployConf.apply
  )
  implicit val environmentFormat: YamlFormat[Environment] = yamlFormat5(
    Environment.apply
  )
  implicit val configFormat: YamlFormat[Config] = yamlFormat3(Config.apply)
}
