(ns kiranomi.components.rrecognition
  (:require [re-frame.core :as rf]
            [reagent.core :refer [atom]]
            [kiranomi.components.common :as c]
            [kiranomi.components.calender :as cal]))

(def data
  (list 622 623 624 702 703))

(defn gen-key []
  (gensym "key-"))

(defn rr-form []
  (fn []
    [c/modal
     [:div]
     [:div.container.mt-3>div.row.justify-content-center
      [:div.mb-4 [:h2 "決定しました"]]
      [:div.container.mb-2>div.row.justify-content-center>center
       [:button.btn-warning.btn-lg.text-white.btn
        {:on-click #(rf/dispatch [:remove-modal])}
        [:h2.mx-2.mt-1.mb-1 "閉じる"]]]]
     ]))

(defn home-page []
  (let [a (atom 0)]
      [:div.mt-5
       [:div.container.mt-5.mb-0 {:style
                                  {:width "80%"
                                   :border "0.2em solid #65bf92"
                                   :background-color "#65bf92"
                                   :color "white"
                                   :border-top-left-radius "1em"
                                   :border-top-right-radius "1em"}}
        [:div.text-center [:h2.my-1 "選 択"]]]
       [:div.container>div.row.justify-content-center>div.nav-stacked.scrollable
        {:style {:width "80%"}}
        (doall
         (map
          (fn [i]
            [:div.container.ml-0>diw.row.justify-content-center.mt-1.ml-0
             {:style {:width "100%"}
              :key (gen-key)}
             [:div.col-4 [:h2.mb-0.mt-1 (let [t (mod (dec (quot i 100)) 12)]
                                     (if (> t @a)
                                       (do (reset! a t)
                                           (nth cal/months t)) ""))]]
             [:div.col-2.offset-3
              [:div.d-flex
               [:i.far.fa-circle.fa-xs {:style {:margin-top "1.5em"}}]
               [:div.text-right {:style {:width "2em"}}
                [:h2.mb-0.mt-1 {:style {:width "2em"}} (mod i 100)]]]]
             (let [s (seq @(rf/subscribe [:value [(keyword (str i))]]))]
               (cond
                 (some #(= \t %) s) [:div.col-2.offset-1.mt-2
                                     {:style {:color "#ff7979"}}
                                     [:i.fas.fa-circle.fa-2x]]
                 (some #(= \q %) s) [:div.col-2.offset-1.mt-2
                                     {:style {:color "rgb(137, 43, 236)"}}
                                     [:i.fas.fa-question.fa-2x
                                      {:style {:margin-left "0.1em"}}]]
                 (some #(= \f %) s) [:div.col-2.offset-1.mt-2
                                     {:style {:color "#5757ff"}}
                                     [:i.fas.fa-times.fa-2x
                                      {:style {:margin-left "0.15em"}}]]))])
          data))]
       [:footer.footer
        [:div.container>div.row.justify-content-center
         [:div.col-4
           [:button.btn-danger.btn.btn-lg.text-white
            {:on-click #(set! (-> js/document .-location .-href) "/#/iinput")}
            [:h2.mt-1.mx-2.mb-2 "戻る"]]]
         [:div.col-4.offset-4
          [:button.btn-warning.btn.btn-lg.text-white
           {:on-click #(rf/dispatch [:set-modal rr-form])}
           [:h2.mt-1.mx-2.mb-2 "決定"]]]]]]))
