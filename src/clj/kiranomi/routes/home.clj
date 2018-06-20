(ns kiranomi.routes.home
  (:require [kiranomi.layout :as layout]
            [kiranomi.db.core :as db]
            [clojure.java.io :as io]
            [reitit.ring.coercion :as rrc]
            [reitit.coercion.spec :as rcs]
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
                        (response/header "Content-Type" "text/plain; charset=utf-8")))}]
   ["/minput" {:get {:coercion rcs/coercion
                    :parameters {:query {:title string?
                                         :owner string?
                                         :date int?}}
                    :handler (fn [param]
                               (println (-> param :query-params))
                               (println
                                (merge
                                 param
                                 {:session (-> param :query-params)}
                                       (ring.util.response/redirect "/iinput")))
                               (merge
                                param
                                {:session (-> param :query-params)}
                                (ring.util.response/redirect "/iinput")))}}]
   ["/iinput" {:get home-page}]
   ["/hoge" {:get home-page}]])

