(ns kiranomi.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [kiranomi.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[kiranomi started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[kiranomi has shut down successfully]=-"))
   :middleware wrap-dev})
