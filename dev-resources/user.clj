(ns user
  (:require
    [clojure-game-geek-new.schema :as s]
    [com.walmartlabs.lacinia :as lacinia]
    [com.walmartlabs.lacinia.pedestal :as lp]
    [io.pedestal.http :as http]
    [clojure.java.browse :refer [browse-url]]
    [clojure.walk :as walk]
    [clojure-game-geek-new.test-utils :refer [simplify]]
    [clojure-game-geek-new.system :as system]
    [com.stuartsierra.component :as component]))

(defonce system nil)

(defn q
  [query-string]
  (-> system
      :schema-provider
      :schema
      (lacinia/execute query-string nil nil)
      simplify))

(defn start
  []
  (alter-var-root #'system (fn [_]
                             (-> (system/new-system)
                                 component/start-system)))
  (browse-url "http://localhost:8888/")
  :started)

(defn stop
  []
  (when (some? system)
    (component/stop-system system)
    (alter-var-root #'system (constantly nil)))
  :stopped)

(comment
  (start)
  (stop)
  )