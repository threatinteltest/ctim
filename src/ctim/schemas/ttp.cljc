(ns ctim.schemas.ttp
  (:require [ctim.lib.schema :refer [describe]]
            [ctim.schemas.common :as c]
            [ctim.schemas.relationships :as rel]
            [ctim.schemas.vocabularies :as v]
            [schema-tools.core :as st]
            [schema.core :as s]))

(s/defschema AttackPattern
  "See http://stixproject.github.io/data-model/1.2/ttp/AttackPatternType/"
  (st/optional-keys
   {:title s/Str
    :description c/Markdown
    :short_description s/Str
    :capec_id (describe
               s/Str
               (str "a reference to a particular entry within the Common Attack"
                    " Pattern Enumeration and Classification"))}))

(s/defschema MalwareInstance
  "See http://stixproject.github.io/data-model/1.2/ttp/MalwareInstanceType/"
  (st/optional-keys
   {:title s/Str
    :description c/Markdown
    :short_description s/Str
    :type (describe [v/MalwareType]
                    "a characterization of what type of malware this")
    }))

(s/defschema Behavior
  "See http://stixproject.github.io/data-model/1.2/ttp/BehaviorType/"
  (st/optional-keys
   {:attack_patterns (describe [AttackPattern]
                                 "one or more Attack Patterns for this TTP")

    :malware_type (describe [MalwareInstance]
                              "one or more instances of Malware for this TTP")
    ;; Not provided: exploits ; It is abstract
    }))

(s/defschema Infrastructure
  "See http://stixproject.github.io/data-model/1.2/ttp/InfrastructureType/"
  (st/optional-keys
   {:title s/Str
    :description (describe
                  c/Markdown
                  (str "text (Markdown) description of specific classes or instances of"
                       " infrastructure utilized for cyber attack"))
    :short_description s/Str
    :type (describe v/AttackerInfrastructure
                   "represents the type of infrastructure being described")
    }))

(s/defschema Resource
  "See http://stixproject.github.io/data-model/1.2/ttp/ResourceType/"
  (st/optional-keys
   {:tools (describe c/Tool "The tool leveraged by this TTP")
    :infrastructure (describe
                     Infrastructure
                     "infrastructure observed to have been utilized for cyber attack")
    :personas c/Identity}))


(s/defschema VictimTargeting
  "See http://stixproject.github.io/data-model/1.2/ttp/VictimTargetingType/"
  (st/optional-keys
   {:identity (describe
               c/Identity
               "infrastructure observed to have been utilized for cyber attack")
    :targeted_systems (describe [v/SystemType] "type of system that is targeted")
    :targeted_information (describe [v/InformationType]
                                    "a type of information that is targeted")
    :targeted_observables (describe [c/Observable] "targeted observables")})) ;; Was targeted_technical_details

(s/defschema TypeIdentifier
  (s/enum "ttp"))

(s/defschema TTP
  "See http://stixproject.github.io/data-model/1.2/ttp/TTPType/"
  (merge
   c/BaseEntity
   c/DescribableEntity
   c/SourcableObject
   {:type TypeIdentifier
    :valid_time (describe
                 c/ValidTime
                 "a timestamp for the definition of a specific version of a TTP item")}
   (st/optional-keys
    {:intended_effect (describe
                       [v/IntendedEffect]
                       "the suspected intended effect for this TTP")
     :behavior (describe
                Behavior
                (str "describes the attack patterns, malware, or exploits that"
                     " the attacker leverages to execute this TTP"))
     :resources (describe
                 Resource
                 "infrastructure or tools that the adversary uses to execute this TTP")
     :victim_targeting (describe VictimTargeting
                                 (str "characterizes the people, organizations,"
                                      " information or access being targeted"))
     :exploit_targets (describe
                       rel/RelatedExploitTargets
                       (str "potential vulnerability, weakness or configuration"
                            " targets for exploitation by this TTP"))
     :related_TTPs (describe
                    rel/RelatedTTPs
                    (str
                     "specifies other TTPs asserted to be related to this cyber"
                     " threat TTP"))
     :source (describe s/Str "source of this cyber threat TTP")
     :kill_chains [v/KillChain]})
   {;; Extension fields:
    :ttp_type (describe s/Str "type of this TTP")
    :indicators (describe rel/RelatedIndicators "related indicators")
    ;; Not provided: handling
    ;; Not provided: related_packages (deprecated)
    }))

(s/defschema NewTTP
  (st/merge
   TTP
   c/NewBaseEntity
   (st/optional-keys
    {:type TypeIdentifier
     :valid_time c/ValidTime})))

(s/defschema StoredTTP
  "An ttp as stored in the data store"
  (c/stored-schema "ttp" TTP))
