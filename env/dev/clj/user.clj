(ns user
  (:require [kiranomi.config :refer [env]]
            [clojure.spec.alpha :as s]
            [expound.alpha :as expound]
            [mount.core :as mount]
            [kiranomi.figwheel :refer [start-fw stop-fw cljs]]
            [kiranomi.core :refer [start-app]]
            [kiranomi.db.core]
            [conman.core :as conman]
            [luminus-migrations.core :as migrations]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(defn start []
  (mount/start-without #'kiranomi.core/repl-server))

(defn stop []
  (mount/stop-except #'kiranomi.core/repl-server))

(defn restart []
  (stop)
  (start))

(defn restart-db []
  (mount/stop #'kiranomi.db.core/*db*)
  (mount/start #'kiranomi.db.core/*db*)
  (binding [*ns* 'kiranomi.db.core]
    (conman/bind-connection kiranomi.db.core/*db* "sql/queries.sql")))

(defn reset-db []
  (migrations/migrate ["reset"] (select-keys env [:database-url])))

(defn migrate []
  (migrations/migrate ["migrate"] (select-keys env [:database-url])))

(defn rollback []
  (migrations/migrate ["rollback"] (select-keys env [:database-url])))

(defn create-migration [name]
  (migrations/create name (select-keys env [:database-url])))


