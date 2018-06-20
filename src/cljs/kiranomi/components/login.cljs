(ns kiranomi.components.login
  (:require [ajax.core :as ajax]
            [re-frame.core :as rf]
            [reagent.core :refer [atom]]
            [kiranomi.components.common :as c]))

(defn login! [fields error]
  (rf/dispatch [:remove-modal])
  (set! (-> js/document .-location .-href) "/#/home"))

(defn login-form []
  (let [fields (atom {})
        error (atom nil)]
    (fn []
      [c/modal
       [:div "ログイン"]
       [:div
        [:div
         [:div.well.well-lg
          [c/text-input "名前" :id "名前を入力して下さい" fields]
          [c/password-input "パスワード" :pass "パスワードを入力して下さい" fields]
          (when-let [error @error]
            [:div.alert.alert-danger error])]
         [:div.container
          [:div.row
           [:div.col-4.text-center
            [:button.btn.btn-danger.btn-lg
             {:on-click #(rf/dispatch [:remove-modal])}
             [:h2.pt-1.px-2.mb-1 "戻る"]]]
           [:div.col-5.offset-3.text-center
            [:button.btn.btn-warning.btn-lg.text-white
             {:on-click #(login! fields error)
              :style {:color "orange"}}
             [:h2.pt-1.px-2.mb-1 "ログイン"]]]
           ]]]]])))

(defn login-button []
  [:button.btn.btn-lg.btn-warning
   {:style {:color "white"}
    :on-click #(rf/dispatch [:set-modal login-form])} [:h2.pt-1.px-2.mb-1 "ログイン"]])

