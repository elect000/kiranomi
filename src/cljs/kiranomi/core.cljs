(ns kiranomi.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]

            [goog.events :as events]
            [goog.history.EventType :as HistoryEventType]
            [markdown.core :refer [md->html]]
            [ajax.core :refer [GET POST]]
            [kiranomi.ajax :refer [load-interceptors!]]
            [kiranomi.events]
            [reitit.core :as reitit]
            [clojure.string :as string]

            [kiranomi.components.login :as login]
            [kiranomi.components.calender :as calender])
  (:import goog.History))

(defn modal []
  (when-let [session-model @(rf/subscribe [:modal])]
    [session-model]))

(defn nav-link [uri title page]
  [:li.nav-item
   {:class (when (= page @(rf/subscribe [:page])) "active")}
   [:a.nav-link {:href uri} title]])

(defn navbar []
  [:nav.navbar.navbar-dark.bg-primary.navbar-expand-md
   {:role "navigation"}
   [:button.navbar-toggler.hidden-sm-up
    {:type "button"
     :data-toggle "collapse"
     :data-target "#collapsing-navbar"}
    [:span.navbar-toggler-icon]]
   [:a.navbar-brand {:href "#/"} "kiranomi"]
   [:div#collapsing-navbar.collapse.navbar-collapse
    [:ul.nav.navbar-nav.mr-auto
     [nav-link "#/" "Home" :home]
     [nav-link "#/about" "About" :about]]]])

(defn about-page []
  [:div.container
   [:div.row
    [:div.col-md-4
     [:img {:src "/img/warning_clojure.png"}]]]])

(defn home-page []
  [:div.container
   [:div.row.justify-content-center
    [:div.col-md-4.offset-4
     [login/login-button]]]
   ;;  [:h2.alert.alert-info "Tip: try pressing CTRL+H to open re-frame tracing menu"]]
   ;; (when-let [docs @(rf/subscribe [:docs])]
   ;;   [:div.row>div.col-sm-12
   ;;    [:div {:dangerouslySetInnerHTML
   ;;           {:__html (md->html docs)}}]]
   ;;   )
   ])

(defn calender-page []
  [:div
   (calender/cal-array->hiccup
    (calender/get-cal-array @(rf/subscribe [:month-add])))])

(def pages
  {:home #'home-page
   :about #'about-page
   :calender #'calender-page})

(defn page []
  [:div
   [modal]
   [navbar]
   [(pages @(rf/subscribe [:page]))]])

;; -------------------------
;; Routes

(def router
  (reitit/router
    [["/" :home]
     ["/about" :about]
     ["/home" :calender]]))

;; -------------------------
;; History
;; must be called after routes have been defined
(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
      HistoryEventType/NAVIGATE
      (fn [event]
        (let [uri (or (not-empty (string/replace (.-token event) #"^.*#" "")) "/")]
          (rf/dispatch
            [:navigate (reitit/match-by-path router uri)]))))
    (.setEnabled true)))

;; -------------------------
;; Initialize app
(defn fetch-docs! []
  (GET "/docs" {:handler #(rf/dispatch [:set-docs %])}))

(defn mount-components []
  (rf/clear-subscription-cache!)
  (r/render [#'page] (.getElementById js/document "app")))

(defn init! []
  (rf/dispatch-sync [:navigate (reitit/match-by-name router :home)])
  
  (load-interceptors!)
  (fetch-docs!)
  (hook-browser-navigation!)
  (mount-components))
