(ns kiranomi.components.recognition
  (:require [ajax.core :as ajax]
            [re-frame.core :as rf]
            [reagent.core :refer [atom]]
            [kiranomi.components.common :as c]
            [kiranomi.components.calender :refer [months]]))

(defn gen-key []
  (gensym "key-"))

(defn r-scroller [dates]
  (let [d (atom 0)]
    [:div.container>div.row.justify-content-center
     [:div.mt-4 {:style {:width "100%"}}
      [:center>div.pt-2
       {:style {:width "70%"
                :background-color "#65bf92"
                :color "white"
                :border-top-left-radius "1em"
                :border-top-right-radius "1em"} } [:h2.mb-0 "Dates"]]]
     [:div.nav-stacked.scrollable
      {:style {:width "70%"}}
      (doall
       (for [i (sort dates)]
         [:div.nav-item {:key (gen-key)}
          (if (> (quot i 100) @d)
            (do
              (reset! d (quot i 100))
              [:div.d-flex.py-2
               ;; {:style {:border-bottom "2px solid rgba(63, 29, 169, 0.6)"}}
               [:div.col-6.text-left.px-3
                [:h2.mb-1 {:style {:margin-bottom "0"}}
                 (str (nth months (dec (quot i 100))) "")]]
               [:div.col-4.offset-2
                [:div.row
                 [:div.col-3.pt-2 [:i.far.fa-circle.fa-xs]]
                 [:div.col-6.text-right
                  [:div->h2.mb-1 {:style {:width "1em"}} (mod i 100)]]]]])
            [:div.d-flex.p-2
             [:div.col-4.offset-8.text-center
              ;;{:style {:border-bottom "2px solid rgba(63, 29, 169, 0.6)"}}
              [:div.row
               [:div.col-3.pt-2 [:i.far.fa-circle.fa-xs]]
               [:div.col-6.text-right
                [:div->h2.mb-1 {:style {:width "1em"}} (mod i 100)]]]]])]))]]))

(defn dates-list []
  (let [dates @(rf/subscribe [:dates])]
    [r-scroller dates]))


(defn recognition []
  [:div.col-10.offset-1.mb-4
   {:style {:text-align "center"}}
   [:h2 "この内容で" [:wbr] "送信しますか？"]])

(defn buttons []
  [:div.container->div.row.justify-content-center.mx-3
   [:div.col-4>button.btn.btn-warning.btn-lg.text-white
    {:on-click #(set! (-> js/document .-location .-href) "/#/home")}
    [:h3.pt-1.px-2.mb-1 "戻る"]]
   [:div.col-4.offset-4
    [:button.btn.btn-danger.btn-lg
     {:on-click #(set! (-> js/document .-location .-href) "/#/share")}
     [:h3.pt-1.px-2.mb-1 "はい"]]]])

