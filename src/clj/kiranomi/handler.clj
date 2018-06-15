(ns kiranomi.handler
  (:require 
            [kiranomi.layout :refer [error-page]]
            [kiranomi.routes.home :refer [home-routes]]
            [kiranomi.routes.services :refer [service-routes]]
            [reitit.swagger-ui :as swagger-ui]
            [reitit.ring :as ring]
            [ring.util.http-response :as response]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.middleware.webjars :refer [wrap-webjars]]
            [kiranomi.env :refer [defaults]]
            [mount.core :as mount]
            [kiranomi.middleware :as middleware]))

(mount/defstate init-app
  :start ((or (:init defaults) identity))
  :stop  ((or (:stop defaults) identity)))


(mount/defstate app
  :start
  (ring/ring-handler
    (ring/router
      [(home-routes)
       (service-routes)])
    (ring/routes
      (swagger-ui/create-swagger-ui-handler
        {:path "/swagger-ui"
         :url "/api/swagger.json"
         :config {:validator-url nil}})
      (ring/create-resource-handler
        {:path "/"})
      (wrap-content-type
        (wrap-webjars (constantly nil)))
      (ring/create-default-handler
        {:not-found
         (constantly (error-page {:status 404, :title "404 - Page not found"}))
         :method-not-allowed
         (constantly (error-page {:status 405, :title "405 - Not allowed"}))
         :not-acceptable
         (constantly (error-page {:status 406, :title "406 - Not acceptable"}))}))))
