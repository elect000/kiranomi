(ns kiranomi.components.share
  (:require [ajax.core :as ajax]
            [re-frame.core :as rf]
            [reagent.core :refer [atom]]
            [kiranomi.components.common :as c]))

(defn share []
  [:div.row.justify-content-center
   [:div.mt-5 [:h1 "予定を作成しました"]]
   [:div.mt-5.text-center>img {:src "/img/kanpai.png"
                               :style {:width "70%"}}]

   [:div.mt-5.text-center
    {:style {:width "100%"}}
    [:div.container>div.row.justify-content-center
     [:div.col-4
      [:a
       {:href "https://social-plugins.line.me/lineit/share?url=https://www.google.com"
        }
       [:img {:src "/img/share-d.png"
              :width "50%"}]]]
     [:div.col-4
      [:div.fb-share-button
       {:data-href "https://www.google.com"
        :data-layout "button"
        :data-size "large"
        :data-mobile-iframe "true"}
       [:a {:target "_blank"
            :href "https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fdevelopers.facebook.com%2Fdocs%2Fplugins%2F&amp;src=sdkpreparse"
            :class "fb-xfbml-parse-ignore"}
         [:img {:src "/img/facebook.png"
                :width "50%"}]]]]
     [:div.col-4
      [:i.fas.fa-share-alt.fa-4x.mt-1]]]
    [:footer.footer [:button.btn.btn-danger.btn-lg
                     {:on-click #(set! (-> js/document .-location .-href) "/#/home")}
                     [:h2.px-2.pt-1.mb-1 "再編集"]]]]])
