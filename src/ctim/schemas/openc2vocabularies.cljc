(ns ctim.schemas.openc2vocabularies
  (:require [schema.core :as s]))


(def COAType
  "See https://github.com/OpenC2-org/subgroup-stix/blob/master/schema/openc2_stix_coa.xsd"
  (s/enum   "alert",
            "allow",
            "augment",
            "contain",
            "delete",
            "deny",
            "detonate",
            "distill",
            "get",
            "investigate",
            "locate",
            "mitigate",
            "modify",
            "move",
            "notify",
            "pause",
            "query",
            "redirect",
            "remediate",
            "report",
            "response",
            "restart",
            "restore",
            "resume",
            "save",
            "scan",
            "set",
            "snapshot",
            "start",
            "stop",
            "substitute",
            "sync",
            "throttle",
            "update",
            "other"))

(def ActuatorType
  (s/enum "endpoint",
            "endpoint.digital-telephone-handset",
            "endpoint.laptop",
            "endpoint.pos-terminal",
            "endpoint.printer",
            "endpoint.sensor",
            "endpoint.server",
            "endpoint.smart-meter",
            "endpoint.smart-phone",
            "endpoint.tablet",
            "endpoint.workstation",
            "network",
            "network.bridge",
            "network.firewall",
            "network.gateway",
            "network.guard",
            "network.hips",
            "network.hub",
            "network.ids",
            "network.ips",
            "network.modem",
            "network.nic",
            "network.proxy",
            "network.router",
            "network.security_manager",
            "network.sense_making",
            "network.sensor",
            "network.switch",
            "network.vpn",
            "network.wap",
            "process",
            "process.aaa-server",
            "process.anti-virus-scanner",
            "process.connection-scanner",
            "process.directory-service",
            "process.dns-server",
            "process.email-service",
            "process.file-scanner",
            "process.location-service",
            "process.network-scanner",
            "process.remediation-service",
            "process.reputation-service",
            "process.sandbox",
            "process.virtualization-service",
            "process.vulnerability-scanner",
            "other"))

(def ModifierType
  (s/enum "delay"
         "duration"
         "frequency"
         "response"
         "time"
         "reportTo"))

(def LocationClass
  (s/enum "Internally-Located"
          "Externally-Located"
          "Co-Located"
          "Mobile"
          "Unknown"))

(def LossDuration
  (s/enum "Permanent"
          "Weeks"
          "Days"
          "Hours"
          "Minutes"
          "Seconds"
          "Unknown"))
