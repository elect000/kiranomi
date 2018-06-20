(ns kiranomi.components.see-other
  (:require [kiranomi.components.calender :as cal]
            [re-frame.core :as rf]))

(defn gen-key []
  (gensym "key-"))

(def data-qa
  {:622 {:t 4 :f 4 :q 1}
   :623 {:t 3 :f 3 :q 3}
   :624 {:t 5 :f 1 :q 3}
   :702 {:t 1 :f 4 :q 4}
   :703 {:t 4 :f 5 :q 0}})

(def data
  (map #(name %) (keys data-qa)))

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
      [:div.text-center [:h2.my-1 "集計結果"]]]
     [:div.container>div.row.justify-content-center>div.nav-stacked.scrollable
      {:style {:width "80%"}}
      [:div.col-6.offset-6.p-0.mb-3
       [:div.container.p-0.mt-1>div.row.justify-content-center.btn-group
        {:style {:width "100%"
                 :margin-left "0"}}
        [:div.col-4.btn.btn-info.disabled [:i.far.fa-circle
                     {:key (gen-key)}]]
        [:div.col-4.btn.btn-info.disabled [:i.fas.fa-times
                     {:key (gen-key)}]]
        [:div.col-4.btn.btn-info.disabled [:i.fas.fa-question
                     {:key (gen-key)}]]]]
      (doall
       (map
        (fn [i]
          (let [s (seq @(rf/subscribe [:value [(keyword (str i))]]))
                add-t (if (some #(= \t %) s) 1 0)
                add-f (if (some #(= \f %) s) 1 0)
                add-q (if (some #(= \q %) s) 1 0)]
           [:div.container.ml-0.px-0>div.row.justify-content-center.mt-0.ml-0
            {:style {:width "100%"}
             :key (gen-key)}
            [:div.col-6.text-right.pt-2
             {:width "100%"}
             [:div.container>div.row
              [:div.col-8.pl-0
               [:h2.pb-1.mb-0
                {:width "100%"}
                (let [t (mod (dec (quot i 100)) 12)]
                  (if (> t @a)
                    (do (reset! a t)
                        (nth cal/months t)) ""))]]
              [:div.col-4.text-right
               [:h2.pb-1.mb-0
                {:style {:width "1.3em"}} (mod i 100)]]]
             ]
            [:div.col-6.px-0.pt-2 {:style {:border-left "2px solid gray"}}
             [:div.container.p-0>div.row.justify-content-center.m-0.text-center
              [:div.col-4 [:h3.mb-0 (+ add-t (:t ((keyword (str i)) data-qa)))]]
              [:div.col-4 [:h3.mb-0 (+ add-f (:f ((keyword (str i)) data-qa)))]]
              [:div.col-4 [:h3.mb-0 (+ add-q (:q ((keyword (str i)) data-qa)))]]]]]))
        data))]
     [:footer.footer
      [:div.container>div.row
       [:div.col-4
        [:button.btn-danger.btn.btn-lg.text-white
         {:on-click #(set! (-> js/document .-location .-href) "/#/iinput")}
         [:h2.mt-1.mx-2.mb-2 "戻る"]]]]]]))
