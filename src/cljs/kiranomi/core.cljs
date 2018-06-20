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
            [kiranomi.components.calender :as calender]
            [kiranomi.components.recognition :as recognition]
            [kiranomi.components.share :as share]
            [kiranomi.components.input :as input]
            [kiranomi.components.rrecognition :as rr]
            [kiranomi.components.see-other :as so]
            [kiranomi.components.planlist :as pl]
            [kiranomi.components.irecognition :as ir])
  (:import goog.History))

(defn modal []
  (when-let [session-model @(rf/subscribe [:modal])]
    [session-model]))

(defn nav-link [uri title page]
  [:li.nav-item
   {:class (when (= page @(rf/subscribe [:page])) "active")}
   [:a.nav-link {:href uri} title]])

(defn navbar []
  [:nav.navbar.navbar-dark.navbar-expand-md
   {:role "navigation"
    :style {:background-color "orange"}}
   ;; [:button.navbar-toggler.hidden-sm-up
   ;;  {:type "button"
   ;;   :data-toggle "collapse"
   ;;   :data-target "#collapsing-navbar"}
   ;;  [:span.navbar-toggler-icon]]
   [:div.container
    {:style {:width "100%"}}
    [:div.d-flex.justify-content-center
     {:style {:width "100%"}}
     [:div.col-2
      [:a.navbar-brand {:href "#/"} [:img {:src "img/honou.png"
                                           :width "50"
                                           :height "60"}]]]
     ;; [:div#collapsing-navbar.collapse.navbar-collapse
     ;;  [:ul.nav.navbar-nav.mr-auto
     ;;   [nav-link "#/" "Home" :home]
     ;;   [nav-link "#/about" "About" :about]
     ;;   (when (= :calender @(rf/subscribe [:page]))
     ;;     [nav-link "#/home" "Plan" :about])]]
     [:div.col-8
      (case @(rf/subscribe [:page])
        :calender
        [:div->center
         [:input.form-control-lg.mt-3
          {:type "search"
           :placeholder (if (and
                             @(rf/subscribe [:title])
                             (not= "" (clojure.string/trim @(rf/subscribe [:title]))))
                          @(rf/subscribe [:title]) "タイトルを入力")
           :name "Title"
           :on-change #(rf/dispatch [:set-title (-> % .-target .-value)])}]]
        :recognition
        [:div->center [:label.text-white.h1.mt-3 "確 認"]]
        :share
        [:div->center [:label.text-white.h1.mt-3 "共 有"]]
        :input
        [:div->center [:label.text-white.h1.mt-3 "入 力"]]
        :rrecognition
        [:div->center [:label.text-white.h1.mt-3 "確 認"]]
        :pl
        [:div->center [:label.text-white.h1.mt-3 "リ ス ト"]]
        :ir
        [:div->center [:label.text-white.h1.mt-3 "確 認"]]
        [:div->center [:label.text-white.h1.mt-3 "KIRA☆NOMI"]])]
     (case @(rf/subscribe [:page])
       :calender
       [:div.col-2
        [:span.navbar-toggler-icon.mt-3
         {:style {:width "50px"
                  :height "50px"}
          :on-click #(set! (-> js/document .-location .-href) "/#/pl")}]]
       :input
       [:div.col-2
        [:span.navbar-toggler-icon.mt-3
         {:style {:width "50px"
                  :height "50px"}
          :on-click #(set! (-> js/document .-location .-href) "/#/so")}]]
       [:div.col-2])]]])

(defn about-page []
  [:div.container
   [:div.row
    [:div.col-md-4
     [:img {:src "/img/warning_clojure.png"}]]]])

(defn home-page []
  [:div.container
   [:div.row.justify-content-center
    [:div.col-md-4.text-center
     {:style {:margin "60%"}}
     [login/login-button]]]
   ;;  [:h2.alert.alert-info "Tip: try pressing CTRL+H to open re-frame tracing menu"]]
   ;; (when-let [docs @(rf/subscribe [:docs])]
   ;;   [:div.row>div.col-sm-12
   ;;    [:div {:dangerouslySetInnerHTML
   ;;           {:__html (md->html docs)}}]]
   ;;   )
   ])

(defn calender-page []
  [:div.mt-4
   (calender/cal-array->hiccup
    (calender/get-cal-array @(rf/subscribe [:month-add])))
   [:div
    (if (= :calender @(rf/subscribe [:page]))
      [:footer.footer>div.container
       [:div.offset-8.text-center
        [:button.btn.btn-warning.btn-lg.text-white
         {:on-click #(set! (-> js/document .-location .-href) "/#/recognition")}
         [:h3.pt-1.px-2.mb-1 "次へ"]]]])]])

(defn title []
  [:h1 "タイトル: "
   (if (and @(rf/subscribe [:title])
            (not= "" (clojure.string/trim @(rf/subscribe [:title]))))
     @(rf/subscribe [:title])
     "未入力")])

(defn recognition-page []
  [:div.container.mt-5->div.row
   [:div.container.mt-4
    {:style {:width "100%"}}
    [:div.d-flex.justify-content-center>div.text-center
     [title]]]
   (recognition/dates-list)
   [:footer.footer
    (recognition/recognition)
    (recognition/buttons)]])

(defn share-page []
  [:div.container.mt-5
   (share/share)])

(defn input-page []
  [:div.container.mt-5
   (input/input-page)
   (input/footer)])

(defn rrecognition-page []
  [:div.container.mt-5
   (rr/home-page)])

(defn show-other-page []
  [:div.container.mt-5
   (so/home-page)])

(defn pl-page []
  [:div.container.mt-5
   (pl/home-page)])

(defn ir-page []
  [:div.container.mt-5
   (ir/home-page)])

(def pages
  {:home #'home-page
   :about #'about-page
   :calender #'calender-page
   :recognition #'recognition-page
   :share #'share-page
   :input #'input-page
   :rrecognition #'rrecognition-page
   :so #'show-other-page
   :pl #'pl-page
   :ir #'ir-page
   })

(defn page []
  (println "load page")
  (if (= "/iinput" (-> js/location .-pathname))
    (set! (-> js/document .-location .-href) "/#/iinput"))
  [:div
   [modal]
   [navbar]
   [(pages @(rf/subscribe [:page]))]])

;; -------------------------
;; Routes各位

(def router
  (reitit/router
    [["/" :home]
     ["/about" :about]
     ["/home" :calender]
     ["/recognition" :recognition]
     ["/share" :share]
     ["/iinput" :input]
     ["/rr" :rrecognition]
     ["/so" :so]
     ["/pl" :pl]
     ["/ir" :ir]]))

;; -------------------------
;; History
;; must be called after routes have been defined
(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
      HistoryEventType/NAVIGATE
      (fn [event]
        (let [uri (or (not-empty (string/replace (.-token event) #"^.*#" ""))
                      "/")]
          (rf/dispatch
           [:navigate (reitit/match-by-path router uri)])
          )))
    (.setEnabled true)))

;; -------------------------
;; Initialize app
(defn fetch-docs! []
  (GET "/docs" {:handler #(rf/dispatch [:set-docs %])}))

(defn mount-components []
  (rf/clear-subscription-cache!)
  (r/render [#'page] (.getElementById js/document "app"))
  (-> js/LineIt .loadButton))

(defn init! []
  (println "load dispatch")
  (rf/dispatch-sync [:navigate (reitit/match-by-name router :home)])

  (println "load interceptors")
  (load-interceptors!)
  (println "load hook")
  ;; (fetch-docs!)
  (hook-browser-navigation!))
