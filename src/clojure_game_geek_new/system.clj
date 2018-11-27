(ns clojure-game-geek-new.system
  (:require
    [com.stuartsierra.component :as component]
    [clojure-game-geek-new.schema :as schema]
    [clojure-game-geek-new.server :as server]
    [clojure-game-geek-new.db :as db]))

(defn new-system
  []
  (merge (component/system-map)
         (server/new-server)
         (schema/new-schema-provider)
         (db/new-db)))
