(ns kiranomi.components.input
  (:require [reagent.core :refer [atom]]
            [kiranomi.components.common :as c]
            [re-frame.core :as rf]
            [kiranomi.components.common :as c]
            [reagent-forms.core :refer [bind-fields]]))

(def prev-input
  {:622 "622-t"
   :623 "623-f"
   :624 "624-q"
   :702 "702-q"
   :703 "703-q"})

(def data
  (list 622 623 624 702 703))

(defn gen-key []
  (gensym "key-"))

(defn copy-req-modal []
  [c/modal
   [:div]
   [:div.container>div.row.justify-content-center.text-center
    [:h2 "既に入力されたものがあります。" [:wbr] "コピーしますか？"]]
   [:div.container>div.row
    [:div.col-4.text-center
      [:button.btn.btn-warning.btn-lg.text-white
       {:on-click #(rf/dispatch [:remove-modal])}
       [:h2.pt-1.px-2.mb-1 "いいえ"]]]
    [:div.col-4.offset-4.text-center
     [:button.btn.btn-danger.btn-lg
      {:on-click #(do
                    (rf/dispatch [:set-doc prev-input])
                    (rf/dispatch [:remove-modal]))}
      [:h2.pt-1.px-2.mb-1 "はい"]]]]])

(defn input-page []
  (let [fields (atom {:id @(rf/subscribe [:name])})]
    [:div.row.justify-content-center
     [:div.well.well-lg
      {:style {:width "70%"}}
      [:div.d-flex
       [:div.col-10.px-0.mx-0 {:style {:width "100%"}}
        [c/input2 "name" :id "名前を入力してください" fields]]
       [:div.col-2.px-0.mx-0
        [:a.btn.btn-warning.btn-lg.text-white
         {:on-click #(do
                       (rf/dispatch [:set-login])
                       (rf/dispatch [:set-name (:id @fields)])
                       (when (= "testuser" @(rf/subscribe [:name]))
                         (rf/dispatch [:set-modal copy-req-modal])
                         (println "hoge")))}
         [:i.fas.fa-sign-in-alt]]]]]
     [:div.container.mt-5.mb-0 {:style
                                {:width "80%"
                                 :border "0.2em solid #65bf92"
                                 :background-color "#65bf92"
                                 :color "white"
                                 :border-top-left-radius "1em"
                                 :border-top-right-radius "1em"}}
      [:div.text-center [:h2.my-1 "選 択 肢"]]]
     [:div.mt-0.scrollable
      {:style {:width "80%"}}
      [:div.justify-content-cencer
       ;; [:h3 "single-select buttons"]
       ;; [:div.btn-group {:field :single-select :id :unique-position}
       ;;  [:button.btn.btn-default {:key :left} "Left"]
       ;;  [:button.btn.btn-default {:key :middle} "Middle"]
       ;;  [:button.btn.btn-default {:key :right} "Right"]]
       (doall
        (map
         (fn [i]
           (let [s (seq @(rf/subscribe [:value [(keyword (str i))]]))]
               [:div.container.mt-2 {:style {:width "80%"}
                                  :key (gen-key)}
             [:div.row.text-center
              [:div.col-6
               [:h2.m-0 (quot i 100) "/" (mod i 100)]]
              [:div.col-6
               {:key (gen-key)}
               [bind-fields
                [:div.btn-group.btn-group-toggle.d-flex
                 {:data-toggle "buttons"
                  :field :single-select :id (keyword (str i))
                  :key (gen-key)}
                 [:button.btn.btn-info.col-4
                  {:key (str i "-t")
                   :class (if (some #(= \t %) s) :active nil)}
                  [:input {:type "radio"
                           :name "options"
                           :autoComplete "off"
                           :key (gen-key)}]
                  [:i.fas.fa-circle
                   {:key (gen-key)}]]
                 [:button.btn.btn-info.col-4
                  {:key (str i "-f")
                   :class (if (some #(= \f %) s) :active nil)}
                  [:input {:type "radio"
                           :name "options"
                           :autoComplete "off"
                           :key (gen-key)}]
                  [:i.fas.fa-times
                   {:key (gen-key)}]]
                 [:button.btn.btn-info.col-4
                  {:key (str i "-q")
                   :class (if (some #(= \q %) s) :active nil)}
                  [:input {:type "radio"
                           :name "options"
                           :autoComplete "off"
                           :key (gen-key)}]
                  [:i.fas.fa-question {:key (gen-key)}]]]
                c/events]]]]))
         data))]
       ]]))

(defn footer []
  [:footer.footer
   [:div.col-4.offset-8
    [:button.btn-lg.btn-warning.btn.text-white
     {:on-click #(set! (-> js/document .-location .-href) "/#/rr")}
     [:h2.mx-2.mt-1.mb-2 "次へ"]]]])
