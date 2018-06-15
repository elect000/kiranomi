(ns kiranomi.routes.home
  (:require [kiranomi.layout :as layout]
            [kiranomi.db.core :as db]
            [clojure.java.io :as io]
            [kiranomi.middleware :as middleware]
            [ring.util.http-response :as response]))

(defn home-page [_]
  (layout/render "home.html"))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-base
                 middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/docs" {:get (fn [_]
                    (-> (response/ok (-> "docs/docs.md" io/resource slurp))
                        (response/header "Content-Type" "text/plain; charset=utf-8")))}]])

