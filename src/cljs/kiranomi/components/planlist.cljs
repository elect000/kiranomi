(ns kiranomi.components.planlist)

(def title-list
  [{:title "飲み会!"
    :date 618}
   {:title "飲み飲み!!!"
    :date 605}
   {:title "新歓打ち上げ"
    :date 520}])

(defn gen-key []
  (gensym "key-"))

(defn home-page []
  [:div.mt-5
   [:div.container.mt-5.mb-0
    {:style
     {:width "80%"
      :border "0.2em solid #65bf92"
      :background-color "#65bf92"
      :color "white"
      :border-top-left-radius "1em"
      :border-top-right-radius "1em"}}
    [:div.text-center [:h3.my-1 "ロ グ"]]]
   [:div.container>div.row.justify-content-center>div.nav-stacked.scrollable
    {:style {:width "80%"}}
    [:div.mt-3
     (doall
       (map
        (fn [i] [:div.row.mt-1 {:key (gen-key)}
                 [:div.col-6.text-center
                  [:h2.mb-0 (quot (:date i) 100) "/" (mod (:date i) 100)]]
                 [:div.col-6 [:a {:style {:color "#3d8de2"}
                                  :href "#/ir"} [:h4.mb-0.pt-2 (:title i)]]] ])
        title-list))]]
   [:footer.footer
    [:div.container.text-center>div.row
     [:div.col-4
      [:button.btn-danger.btn.btn-lg.text-white
       {:on-click #(set! (-> js/document .-location .-href) "/#/home")}
       [:h2.mt-1.mx-2.mb-2 "戻る"]]]
     ]]])

